package com.cypcode.todo.service;

import com.cypcode.todo.entity.TodoEntity;
import com.cypcode.todo.entity.TodoEntity.Priority;
import com.cypcode.todo.mapper.TodoMapper;
import com.cypcode.todo.model.*;
import com.cypcode.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository repo;
    private final TodoMapper mapper;

    public TodoPage list(String status, String priorityStr, String tag,
                         String search, String sortBy, String sortDir,
                         int page, int size) {

        Boolean completed = switch (status == null ? "all" : status) {
            case "active"    -> false;
            case "completed" -> true;
            default          -> null;
        };
        Priority priority = priorityStr == null ? null : Priority.valueOf(priorityStr.toUpperCase());

        Sort sort = Sort.by(
                "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC,
                toEntityField(sortBy)
        );
        Pageable pageable = PageRequest.of(page, size, sort);

        String searchPat = search != null ? "%" + search.toLowerCase() + "%" : null;
        Page<TodoEntity> result = repo.findFiltered(completed, priority, tag, searchPat, pageable);

        return new TodoPage()
                .content(mapper.toDtoList(result.getContent()))
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .page(result.getNumber())
                .size(result.getSize())
                .first(result.isFirst())
                .last(result.isLast());
    }

    @Transactional
    public Todo create(CreateTodoRequest request) {
        TodoEntity entity = mapper.toEntity(request);
        if (entity.getPriority() == null) entity.setPriority(Priority.MEDIUM);
        return mapper.toDto(repo.save(entity));
    }

    public Todo getById(Long id) {
        return mapper.toDto(findOrThrow(id));
    }

    @Transactional
    public Todo update(Long id, UpdateTodoRequest request) {
        TodoEntity entity = findOrThrow(id);
        mapper.updateEntity(request, entity);
        return mapper.toDto(repo.save(entity));
    }

    @Transactional
    public Todo patch(Long id, PatchTodoRequest request) {
        TodoEntity entity = findOrThrow(id);
        mapper.patchEntity(request, entity);
        return mapper.toDto(repo.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        findOrThrow(id);
        repo.deleteById(id);
    }

    @Transactional
    public void bulkDelete(List<Long> ids) {
        List<TodoEntity> found = repo.findAllByIdIn(ids);
        repo.deleteAll(found);
    }

    @Transactional
    public Todo complete(Long id) {
        TodoEntity entity = findOrThrow(id);
        if (entity.isCompleted()) throw new IllegalStateException("Todo is already completed");
        entity.setCompleted(true);
        entity.setCompletedAt(OffsetDateTime.now());
        return mapper.toDto(repo.save(entity));
    }

    @Transactional
    public Todo reopen(Long id) {
        TodoEntity entity = findOrThrow(id);
        entity.setCompleted(false);
        entity.setCompletedAt(null);
        return mapper.toDto(repo.save(entity));
    }

    public TodoStats stats() {
        long total     = repo.count();
        long completed = repo.countByCompleted(true);
        long active    = repo.countByCompleted(false);
        long overdue   = repo.countOverdue(LocalDate.now());

        Map<String, Integer> byPriority = Map.of(
                "LOW",    (int) repo.countByPriorityAndNotCompleted(Priority.LOW),
                "MEDIUM", (int) repo.countByPriorityAndNotCompleted(Priority.MEDIUM),
                "HIGH",   (int) repo.countByPriorityAndNotCompleted(Priority.HIGH),
                "URGENT", (int) repo.countByPriorityAndNotCompleted(Priority.URGENT)
        );

        return new TodoStats()
                .total((int) total)
                .completed((int) completed)
                .active((int) active)
                .overdue((int) overdue)
                .byPriority(byPriority);
    }

    public List<String> listTags() {
        return repo.findAllDistinctTags();
    }

    private TodoEntity findOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found: " + id));
    }

    private String toEntityField(String sortBy) {
        return switch (sortBy == null ? "createdAt" : sortBy) {
            case "title"    -> "title";
            case "dueDate"  -> "dueDate";
            case "priority" -> "priority";
            default         -> "createdAt";
        };
    }
}
