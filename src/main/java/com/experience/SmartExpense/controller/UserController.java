package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.config.swagger.CommonApiResponses;
import com.experience.SmartExpense.dto.UserRequest;
import com.experience.SmartExpense.dto.UserResponse;
import com.experience.SmartExpense.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Gestión de usuarios")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Listar usuarios",
            description = "Obtiene todos los usuarios"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuarios obtenidos correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = UserResponse.class
                    )
            )
    )
    @CommonApiResponses
    public List<UserResponse> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Usuario por ID",
            description = "Obtiene un usuario por su ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = UserResponse.class
                    )
            )
    )
    @CommonApiResponses
    public UserResponse getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Usuario autenticado",
            description = "Devuelve la información del usuario autenticado mediante JWT"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario autenticado obtenido correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = UserResponse.class
                    )
            )
    )
    @CommonApiResponses
    public UserResponse getCurrentUser(Authentication authentication) {

        String email = authentication.getName();

        return service.getUserByEmail(email);
    }

    @PostMapping
    @Operation(
            summary = "Crear usuario",
            description = "Crea un nuevo usuario"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario creado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = UserResponse.class
                    )
            )
    )
    @CommonApiResponses
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return service.createUser(request);
    }
}
