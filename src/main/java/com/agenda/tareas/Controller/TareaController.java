package com.agenda.tareas.Controller;

import com.agenda.tareas.Domain.Group;
import com.agenda.tareas.Domain.Task;
import com.agenda.tareas.Service.GrupoService;
import com.agenda.tareas.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/agenda/grupos/{groupId}/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private GrupoService grupoService ;

    @GetMapping("/nueva")
    public String formularioNuevaTarea(@PathVariable Long groupId, Model model) {
        Task tarea = new Task();
        model.addAttribute("tarea", tarea);
        model.addAttribute("groupId", groupId);
        return "formularioTarea"; // Crea esta vista HTML
    }

    @PostMapping("/nueva")
    public String crearTarea(@ModelAttribute("tarea") Task tarea, @PathVariable Long groupId) {
        System.out.println(tarea);
        Group grupo = grupoService.obtenerGrupoPorId(groupId);
        tarea.setGroup(grupo);
        tareaService.crearTarea(tarea);
        return "redirect:/agenda/grupos/" + groupId;
    }
}
