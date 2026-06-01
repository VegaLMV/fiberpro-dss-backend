package com.conexion.fiberpro.modules.auth.dtos;

import lombok.Data;

/**
 * Data Transfer Object para la autenticación de usuarios.
 * Encapsula las credenciales provistas desde la capa de presentación.
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
}
