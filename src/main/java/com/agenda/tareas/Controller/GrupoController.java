package com.agenda.tareas.Controller;

import com.agenda.tareas.Domain.Group;
import com.agenda.tareas.Domain.Task;
import com.agenda.tareas.Service.GrupoService;
import com.agenda.tareas.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agenda")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private TareaService tareaService;

    @GetMapping("/grupos")
    public String listarGrupos(Model model) {
        List<Group> grupos = grupoService.listarGrupos();
        model.addAttribute("grupos", grupos);
        return "listarGrupos";
    }

    @GetMapping("/grupos/nuevo")
    public String formularioNuevoGrupo(Model model) {
        model.addAttribute("grupo", new Group());
        return "formularioGrupo"; // Crea esta vista HTML
    }

    @PostMapping("/grupos/nuevo")
    public String crearGrupo(@ModelAttribute("grupo") Group grupo) {
        grupoService.crearGrupo(grupo);
        return "redirect:/agenda/grupos/" + grupo.getId() + "/tareas/nueva";
    }

    @GetMapping("/grupos/{id}")
    public String verGrupo(@PathVariable Long id, Model model) {
        Group grupo = grupoService.obtenerGrupoPorId(id);
        model.addAttribute("grupo", grupo);
        List<Task> tareas = tareaService.listarTareasPorGrupo(grupo);
        model.addAttribute("tareas", tareas);
        return "verGrupo";
    }

    @GetMapping("/grupos/{id}/editar")
    public String formularioEditarGrupo(@PathVariable Long id, Model model) {
        Group grupo = grupoService.obtenerGrupoPorId(id);
        model.addAttribute("grupo", grupo);
        return "formularioGrupo";
    }

    @PostMapping("/grupos/{id}/editar")
    public String editarGrupo(@ModelAttribute("grupo") Group grupo) {
        grupoService.actualizarGrupo(grupo);
        return "redirect:/agenda/grupos/" + grupo.getId();
    }

    @GetMapping("/grupos/{id}/eliminar")
    public String eliminarGrupo(@PathVariable Long id) {
        grupoService.eliminarGrupo(id);
        return "redirect:/agenda/grupos";
    }
}
