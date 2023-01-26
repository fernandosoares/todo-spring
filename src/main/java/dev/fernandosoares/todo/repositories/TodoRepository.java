package dev.fernandosoares.todo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fernandosoares.todo.models.TodoModel;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, UUID> {

}
