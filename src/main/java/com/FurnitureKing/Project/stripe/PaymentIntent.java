package com.FurnitureKing.Project.stripe;

import com.FurnitureKing.Project.models.Client;
import com.stripe.Stripe;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PaymentIntent {

   // @PostMapping("/payment-intents")
   // public ResponseEntity<Object> PaymentIntent() {
   //     Stripe.apiKey = "#############";
   //     CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
   //     PaymentIntentCreateParams params =
   //             PaymentIntentCreateParams.builder()
   //                     .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
   //                     .setCurrency("eur")
   //                     .setAutomaticPaymentMethods(
   //                             PaymentIntentCreateParams.AutomaticPaymentMethods
   //                                     .builder()
   //                                     .setEnabled(true)
   //                                     .build()
   //                     )
   //                     .build();
//
   //     return ResponseEntity.badRequest().build();
   // }

}
