package com.cypcode.todo.controller;

import com.cypcode.todo.api.TodosApi;
import com.cypcode.todo.model.*;
import com.cypcode.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TodosController implements TodosApi {

    private final TodoService service;

    @Override
    public ResponseEntity<TodoPage> listTodos(
            String status, Priority priority, String tag,
            String search, String sortBy, String sortDir,
            Integer page, Integer size) {

        String priorityStr = priority == null ? null : priority.getValue();
        return ResponseEntity.ok(service.list(
                status, priorityStr, tag, search, sortBy, sortDir,
                page == null ? 0 : page,
                size == null ? 20 : size));
    }

    @Override
    public ResponseEntity<Todo> createTodo(CreateTodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @Override
    public ResponseEntity<Todo> getTodoById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<Todo> updateTodo(Long id, UpdateTodoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    public ResponseEntity<Todo> patchTodo(Long id, PatchTodoRequest request) {
        return ResponseEntity.ok(service.patch(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteTodo(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Todo> completeTodo(Long id) {
        return ResponseEntity.ok(service.complete(id));
    }

    @Override
    public ResponseEntity<Todo> reopenTodo(Long id) {
        return ResponseEntity.ok(service.reopen(id));
    }

    @Override
    public ResponseEntity<Void> bulkDeleteTodos(BulkDeleteTodosRequest request) {
        service.bulkDelete(request.getIds());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TodoStats> getTodoStats() {
        return ResponseEntity.ok(service.stats());
    }
}
