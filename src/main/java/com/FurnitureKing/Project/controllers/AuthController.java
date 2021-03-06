package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.*;
import com.FurnitureKing.Project.payload.request.LoginRequest;
import com.FurnitureKing.Project.payload.request.SignupRequest;
import com.FurnitureKing.Project.payload.response.JwtResponse;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.BasketRepository;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.repositories.FavRepository;
import com.FurnitureKing.Project.repositories.RoleRepository;
import com.FurnitureKing.Project.security.jwt.JwtUtils;
import com.FurnitureKing.Project.security.services.UserDetailsImpl;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        ClientRepository clientRepository;
        @Autowired
        RoleRepository roleRepository;
        @Autowired
        PasswordEncoder encoder;
        @Autowired
        JwtUtils jwtUtils;

        private final BasketRepository basketRepository;
        private final FavRepository favRepository;

        public AuthController(BasketRepository basketRepository, FavRepository favRepository) {
            this.basketRepository = basketRepository;
            this.favRepository = favRepository;
        }

        @PostMapping("/sign-in")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            Optional<Client> client = clientRepository.findByEmail(loginRequest.getEmail());
            String idClient = client.get().getId();
            client.get().setNbConnection(client.get().getNbConnection() + 1);
            return ResponseEntity.ok(new JwtResponse(jwt,
                    idClient,
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }

        @PostMapping("/sign-up")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
            if (clientRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            }

            // Create new user's account
            Client client = new Client(
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getCivility(),
                    signUpRequest.getLastName(),
                    signUpRequest.getFirstName(),
                    signUpRequest.getAddress(),
                    signUpRequest.getPostalCode(),
                    signUpRequest.getCity(),
                    signUpRequest.getPhone(),
                    signUpRequest.getNbConnection()
            );

            Set<String> strRoles = signUpRequest.getRoles();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role " + role + " is not found."));
                            roles.add(userRole);
                    }
                });
            }
            client.setCreatedAt(CurrentDateTime.getCurrentDateTime());
            client.setNbConnection(1);
            client.setRoles(roles);
            clientRepository.save(client);
            Basket basket = new Basket(client,CurrentDateTime.getCurrentDateTime());
            basket.setBasketTotalPrice(0.0);
            basketRepository.insert(basket);
            Fav fav = new Fav(client,CurrentDateTime.getCurrentDateTime());
            List<Product> listProduct = new ArrayList<>();
            fav.setProducts(listProduct);
            favRepository.insert(fav);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
}
