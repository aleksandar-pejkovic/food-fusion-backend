package dev.alpey.foodfusionbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alpey.foodfusionbackend.service.auth.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }
}
