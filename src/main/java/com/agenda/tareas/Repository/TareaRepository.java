package com.agenda.tareas.Repository;

import com.agenda.tareas.Domain.Group;
import com.agenda.tareas.Domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Task, Long> {
    List<Task> findByGroup(Group grupo);
}
