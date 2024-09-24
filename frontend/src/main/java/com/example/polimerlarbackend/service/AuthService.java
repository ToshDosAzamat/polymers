package com.example.polimerlarbackend.service;

import com.example.polimerlarbackend.dto.AuthRequest;
import com.example.polimerlarbackend.dto.AuthResponse;

public interface AuthService {
    AuthResponse signIn(AuthRequest authRequest);
}
