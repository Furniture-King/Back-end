package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.models.ERole;
import com.FurnitureKing.Project.models.Role;
import com.FurnitureKing.Project.payload.request.LoginRequest;
import com.FurnitureKing.Project.payload.request.SignupRequest;
import com.FurnitureKing.Project.payload.response.JwtResponse;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.ClientRepository;
import com.FurnitureKing.Project.repositories.RoleRepository;
import com.FurnitureKing.Project.security.jwt.JwtUtils;
import com.FurnitureKing.Project.security.services.UserDetailsImpl;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import com.FurnitureKing.Project.utils.Password;
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

//@RestController
//public class AuthController {
//
//private final ClientRepository clientRepository;
//
//@Autowired
//public AuthController(ClientRepository clientRepository) {
//    this.clientRepository = clientRepository;
//}
//
//
///* Sign-up */
//@PostMapping(value = "/clients/sign-up")
//public ResponseEntity<Client> addClient(@RequestBody Client client){
//    client.setCreatedAt(CurrentDateTime.getCurrentDateTime());
//    client.setNbConnection(1);
//    String[] hshPsw = Password.Hash(client.getPasswordHash());
//    client.setPasswordHash(hshPsw[0]);
//    client.setPasswordSalt(hshPsw[1]);
//    clientRepository.insert(client);
//    return ResponseEntity.ok().body(client);
//}
//
///* Sign-in check */
//@PostMapping(value = "/clients/sign-in")
//public Optional<Client> checkClient(@RequestBody Client data){
//    Optional<Client> client = clientRepository.findByEmail(data.getEmail());
//    final Boolean[] bool = {false};
//    client.ifPresent(c -> {
//        c.setNbConnection(c.getNbConnection() + 1);
//        bool[0] = Password.Check(data.getPasswordHash(), c.getPasswordSalt());
//    });
//    return client;
//}
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
        @PostMapping("/sign-in")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
            System.out.println(loginRequest.getEmail() + " " + loginRequest.getPasswordHash());
            System.out.println("apres ça ca bug ?");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPasswordHash()));
            System.out.println("apres ça ca bug ?");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("apres ça ca bug ?");
            String jwt = jwtUtils.generateJwtToken(authentication);
            System.out.println("apres ça ca bug ?");
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getEmail(),
                    userDetails.getUsername(),
                    roles));
        }
        @PostMapping("/sign-up")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
            //System.out.println(signUpRequest.getEmail() + " " + signUpRequest.getFirstName() + " " + signUpRequest.getPassword() + " " + signUpRequest.getRoles() );
            if (clientRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }

            // Create new client account
            String[] hshPsw = Password.Hash(signUpRequest.getPassword());
            Client client = new Client(
                    signUpRequest.getEmail(),
                    hshPsw[0],
                    hshPsw[1]
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
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            client.setRoles(roles);
            client.setCreatedAt(CurrentDateTime.getCurrentDateTime());
            client.setNbConnection(1);
            clientRepository.save(client);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
}
