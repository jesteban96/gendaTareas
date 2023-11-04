package com.agenda.tareas.Controller;

import com.agenda.tareas.Domain.User;
import com.agenda.tareas.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String loginSubmit(@RequestParam String email, @RequestParam String password, RedirectAttributes attributes) {
        User user = userService.findByEmailAndPassword(email, password);
        System.out.println(user);
        if (user != null) {
            attributes.addFlashAttribute("user", user);
            return "redirect:/agenda/grupos";
        } else {
            attributes.addFlashAttribute("error", "Credenciales inválidas");
            return "redirect:/";
        }
    }

}