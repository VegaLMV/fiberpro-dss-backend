package com.conexion.fiberpro.modules.auth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Servicio encargado de la lógica de autenticación nativa contra SQL Server.
 * No requiere persistencia local, delegando la validación directamente al motor de base de datos.
 */
@Service
public class AuthService {

    @Value("${spring.datasource.econo.jdbc-url}")
    private String jdbcUrl;

    /**
     * Autentica un usuario intentando establecer una conexión física a la base de datos
     * con las credenciales suministradas.
     *
     * @param username El nombre de usuario (Login nativo de SQL Server).
     * @param password La contraseña correspondiente al usuario.
     * @return {@code true} si la conexión es exitosa, indicando credenciales válidas; {@code false} en caso contrario.
     */
    public boolean autenticarContraSqlServer(String username, String password) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
