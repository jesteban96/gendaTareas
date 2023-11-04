package com.agenda.tareas.Service;

import com.agenda.tareas.Domain.Group;
import com.agenda.tareas.Domain.Task;
import com.agenda.tareas.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Task crearTarea(Task tarea) {
        return tareaRepository.save(tarea);
    }

    public List<Task> listarTareasPorGrupo(Group grupo) {
        return tareaRepository.findByGroup(grupo);
    }

}
