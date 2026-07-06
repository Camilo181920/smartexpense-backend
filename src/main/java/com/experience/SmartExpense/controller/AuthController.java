package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.config.swagger.CommonApiResponses;
import com.experience.SmartExpense.dto.AuthResponse;
import com.experience.SmartExpense.dto.LoginRequest;
import com.experience.SmartExpense.dto.RegisterRequest;
import com.experience.SmartExpense.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints de autenticación y registro")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Registrar usuario",
            description = "Crea un nuevo usuario en el sistema y devuelve un token JWT"
    )
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
    @CommonApiResponses
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(
                authService.register(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica un usuario y devuelve un token JWT"
    )
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @CommonApiResponses
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}