package com.agenda.tareas.Service;

import com.agenda.tareas.Domain.Group;
import com.agenda.tareas.Repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    public List<Group> listarGrupos() {
        return grupoRepository.findAll();
    }

    public Group obtenerGrupoPorId(Long id) {
        return grupoRepository.findById(id).orElse(null);
    }

    public void crearGrupo(Group grupo) {
        grupoRepository.save(grupo);
    }

    public void actualizarGrupo(Group grupo) {
        grupoRepository.save(grupo);
    }

    public void eliminarGrupo(Long id) {
        grupoRepository.deleteById(id);
    }
}
