package cap.capgemini.poe.aston.controllers;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cap.capgemini.poe.aston.entities.Role;
import cap.capgemini.poe.aston.entities.RoleName;
import cap.capgemini.poe.aston.entities.User;
import cap.capgemini.poe.aston.exceptions.AppException;
import cap.capgemini.poe.aston.payload.ApiResponse;
import cap.capgemini.poe.aston.payload.JwtAuthenticationResponse;
import cap.capgemini.poe.aston.payload.LoginRequest;
import cap.capgemini.poe.aston.payload.SignUpRequest;
import cap.capgemini.poe.aston.properties.JwtTokenProvider;
import cap.capgemini.poe.aston.repositories.IRoleRepository;
import cap.capgemini.poe.aston.repositories.IUserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (this.userRepository.existsByFirstname(signUpRequest.getFirstname()) && this.userRepository.existsByLastname(signUpRequest.getLastname())) {
            return new ResponseEntity<Object>(new ApiResponse(false, "firstname && lastname combo is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (this.userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<Object>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(null, signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getPassword(), signUpRequest.getEmail(), null
                , null, null, null);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role userRole = this.roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
}
