package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.dto.UserRequest;
import com.experience.SmartExpense.dto.UserResponse;
import com.experience.SmartExpense.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "Users",
        description = "Endpoints para la gestión de usuarios"
)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Devuelve la lista de usuarios registrados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    public List<UserResponse> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener usuario por ID",
            description = "Devuelve la información de un usuario mediante su identificador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    public UserResponse getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Obtener usuario autenticado",
            description = "Devuelve la información del usuario actualmente autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Información obtenida correctamente"),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    public UserResponse getCurrentUser(Authentication authentication) {

        String email = authentication.getName();

        return service.getUserByEmail(email);
    }

    @PostMapping
    @Operation(
            summary = "Crear usuario",
            description = "Crea un nuevo usuario en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "El correo ya está registrado")
    })
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return service.createUser(request);
    }
}