package com.example.authwithkeys.controller;

import com.example.authwithkeys.dto.RegisterRequest;
import com.example.authwithkeys.dto.LoginRequest;
import com.example.authwithkeys.service.AuthService;
import com.example.authwithkeys.service.AuthenticationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    /*private final String secret = "salam";
    private final byte[] privatekey = Files.readAllBytes(Paths.get("C:/workspace/TIPS/auth_with_keys/private_key.pem"));

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        String username= request.getUsername();
        String password= request.getPassword();
        System.out.println("it's ok"+username+" "+password);
        String jwt = Jwts.builder().setSubject(username).
                signWith(SignatureAlgorithm.RS256, privatekey).compact();
        return jwt;
    }

    public AuthController() throws IOException {
    }*/

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){


        System.out.println("dkjsfkjs");

        return authService.login(loginRequest);
    }
}
