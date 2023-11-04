package com.agenda.tareas.Repository;

import com.agenda.tareas.Domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Group, Long> {
}
