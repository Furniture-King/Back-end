package com.FurnitureKing.Project.stripe;

import com.FurnitureKing.Project.models.Basket;
import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.BasketRepository;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.security.jwt.AuthTokenFilter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
class CreatePaymentIntent  {

    private final ClientRepository clientRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public CreatePaymentIntent(ClientRepository clientRepository, BasketRepository basketRepository) {
        this.clientRepository = clientRepository;
        this.basketRepository = basketRepository;
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/payment-intents")
    public ResponseEntity<Object> PaymentIntent(HttpServletRequest request) throws StripeException {

        String email = Jwts.parser().setSigningKey("fkSecretKey").parseClaimsJws(AuthTokenFilter.parseJwt(request)).getBody().getSubject();

        if(!email.isEmpty()){
            Optional<Client> client = clientRepository.findByEmail(email);
            if(client.isPresent()){
                Optional<Basket> basket = basketRepository.getBasketByClient_Id(client.get().getId().toString());
                if(basket.get().getBasketTotalPrice() < 1){
                    return ResponseEntity.badRequest().body(new MessageResponse("Error : Payment below amount needed !"));
                }else{
                    Stripe.apiKey = "sk_test_51LDpezKmKOU8NVzEfs2k0AvFJXc5hhEbrFwIPtEjjD8VJNHOgwuWuvGx8cYgGRHUtBNit7uuUKjbII31yjXTuGAR00FDw4Uk4b";
                    PaymentIntentCreateParams params =
                            PaymentIntentCreateParams.builder()
                                    .setAmount((long) (int) (basket.get().getBasketTotalPrice() * 100))
                                    .setCurrency("eur")
                                    .setAutomaticPaymentMethods(
                                            PaymentIntentCreateParams.AutomaticPaymentMethods
                                                    .builder()
                                                    .setEnabled(true)
                                                    .build()
                                    )
                                    .build();

                    PaymentIntent paymentIntent = PaymentIntent.create(params);

                    return ResponseEntity.ok(paymentIntent.getClientSecret());
                }
            }
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error : Client or Basket don't exist !"));
    }
}
