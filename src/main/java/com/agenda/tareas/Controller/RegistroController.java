package com.agenda.tareas.Controller;

import com.agenda.tareas.Domain.User;
import com.agenda.tareas.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registro(Model model) {
        model.addAttribute("user", new User());
        return "registro";
    }

    @PostMapping
    public String registroSubmit(@ModelAttribute("user") User user, RedirectAttributes attributes) {
        // Verificar si el correo ya está en uso
        Optional<User> existingUser = userService.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            attributes.addFlashAttribute("error", "El correo electrónico ya está en uso.");
            return "redirect:/registro";
        }

        // Crear el nuevo usuario
        userService.createUser(user);
        attributes.addFlashAttribute("success", "¡Registro exitoso! Ahora puedes iniciar sesión.");
        return "redirect:/";
    }

}
