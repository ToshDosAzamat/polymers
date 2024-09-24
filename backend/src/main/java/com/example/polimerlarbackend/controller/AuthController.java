package com.example.polimerlarbackend.controller;

import com.example.polimerlarbackend.dto.AuthRequest;
import com.example.polimerlarbackend.dto.AuthResponse;
import com.example.polimerlarbackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(authService.signIn(authRequest), HttpStatus.OK);
    }

}
