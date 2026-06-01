package com.conexion.fiberpro.modules.auth.controllers;

import com.conexion.fiberpro.modules.auth.dtos.LoginDTO;
import com.conexion.fiberpro.modules.auth.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para la gestión del ciclo de vida de la sesión de los usuarios.
 * Intercepta peticiones de autenticación y administra el estado en la sesión HTTP.
 */
@Controller
@RequestMapping("/auth")
public class LoginController {

    private final AuthService authService;

    /**
     * Inyección de dependencias por constructor.
     *
     * @param authService Servicio responsable de la lógica de validación de credenciales.
     */
    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Muestra la vista del formulario de inicio de sesión.
     *
     * @return El identificador lógico de la vista de login.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Procesa la solicitud de inicio de sesión utilizando el DTO y gestiona la sesión web.
     *
     * @param dto El objeto de transferencia de datos con las credenciales enviadas.
     * @param session La sesión HTTP actual para almacenar el estado de autenticación.
     * @return Una redirección al dashboard en caso de éxito, o de vuelta al formulario con indicador de error.
     */
    @PostMapping("/procesar-login")
    public String procesarLogin(@ModelAttribute LoginDTO dto, HttpSession session) {
        boolean esValido = authService.autenticarContraSqlServer(dto.getUsername(), dto.getPassword());

        if (esValido) {
            session.setAttribute("usuarioLogueado", dto.getUsername());
            return "redirect:/layout";
        } else {
            return "redirect:/auth/login?error=true";
        }
    }

    /**
     * Cierra la sesión activa del usuario y limpia el contexto de seguridad en la sesión HTTP.
     *
     * @param session La sesión HTTP a invalidar.
     * @return Una redirección a la vista de login.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
