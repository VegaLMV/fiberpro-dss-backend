package com.conexion.nominacrud.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
public class LoginController {

    // La URL de tu servidor (sacada de tus logs anteriores)
    private final String DB_URL = "jdbc:sqlserver://26.141.229.180:1433;databaseName=db_econo;encrypt=true;trustServerCertificate=true;";

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Muestra la pantalla de login.html
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        try {
            // 1. INTENTAMOS CONECTARNOS DIRECTO A SQL SERVER
            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            
            // 2. Si SQL Server no lanza error, cerramos la conexión de prueba
            conn.close(); 
            
            // 3. Guardamos el pase de acceso en la sesión de la página web
            session.setAttribute("usuarioLogueado", username);
            
            // 4. Lo enviamos al formulario principal
            return "redirect:/"; 

        } catch (SQLException e) {
            // Si SQL Server rechaza la conexión, cae aquí
            model.addAttribute("error", "Credenciales incorrectas o usuario no existe en SQL Server.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Destruye la sesión web
        return "redirect:/login";
    }
}