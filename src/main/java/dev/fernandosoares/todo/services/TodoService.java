package dev.fernandosoares.todo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fernandosoares.todo.models.TodoModel;
import dev.fernandosoares.todo.repositories.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoModel> getAll() {
        return todoRepository.findAll();
    }

    public TodoModel create(TodoModel todoModel) {
        return todoRepository.save(todoModel);
    }

    public Optional<TodoModel> findById(UUID id) {
        return todoRepository.findById(id);
    }

    public TodoModel save(TodoModel todoModel) {
        return todoRepository.save(todoModel);
    }

    public void delete(TodoModel todoModel) {
        todoRepository.delete(todoModel);
    }

}
