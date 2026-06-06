package com.cypcode.todo.controller;

import com.cypcode.todo.api.TagsApi;
import com.cypcode.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TagsController implements TagsApi {

    private final TodoService service;

    @Override
    public ResponseEntity<List<String>> listTags() {
        return ResponseEntity.ok(service.listTags());
    }
}
