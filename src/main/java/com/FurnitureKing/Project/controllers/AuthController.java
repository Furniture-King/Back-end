package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.models.ShoppingCart;
import com.FurnitureKing.Project.models.ERole;
import com.FurnitureKing.Project.models.Role;
import com.FurnitureKing.Project.payload.request.LoginRequest;
import com.FurnitureKing.Project.payload.request.SignupRequest;
import com.FurnitureKing.Project.payload.response.JwtResponse;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.ShoppingCartRepository;
import com.FurnitureKing.Project.repositories.ClientRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        private final ShoppingCartRepository shoppingCartRepository;

        public AuthController(ShoppingCartRepository shoppingCartRepository) {this.shoppingCartRepository = shoppingCartRepository;}

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

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
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
                    signUpRequest.getNbConnection(),
                    signUpRequest.getFavProduct()
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
            ShoppingCart shoppingCart = new ShoppingCart(client,CurrentDateTime.getCurrentDateTime());
            shoppingCartRepository.insert(shoppingCart);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
}
