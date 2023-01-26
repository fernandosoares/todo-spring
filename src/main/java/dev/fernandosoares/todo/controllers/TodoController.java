package dev.fernandosoares.todo.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fernandosoares.todo.dtos.TodoDTO;
import dev.fernandosoares.todo.models.TodoModel;
import dev.fernandosoares.todo.services.TodoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping()
    public ResponseEntity<List<TodoModel>> todos() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id) {
        Optional<TodoModel> todo = todoService.findById(id);

        if (!todo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No TODO found with the provided UUID.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(todo.get());
    }

    @PostMapping()
    public ResponseEntity<TodoModel> create(@RequestBody @Valid TodoDTO todoDTO) {
        TodoModel todoModel = new TodoModel();
        BeanUtils.copyProperties(todoDTO, todoModel);
        todoModel.setDone(false);
        todoModel.setCreatedAt(LocalDateTime.now());
        todoModel.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todoModel));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid TodoDTO todoDTO) {
        Optional<TodoModel> todo = todoService.findById(id);
        if (!todo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No TODO found with the provided UUID.");
        }

        TodoModel todoModel = todo.get();
        BeanUtils.copyProperties(todoDTO, todoModel);

        todoModel.setId(todo.get().getId());
        todoModel.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(todoService.save(todoModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<TodoModel> todo = todoService.findById(id);
        if (!todo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No TODO found with the provided UUID.");
        }
        TodoModel todoModel = todo.get();
        todoModel.setId(todo.get().getId());
        todoService.delete(todoModel);

        return ResponseEntity.status(HttpStatus.OK).body("Todo was deleted successfully");
    }

}
