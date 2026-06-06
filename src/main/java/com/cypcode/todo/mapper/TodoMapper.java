package com.cypcode.todo.mapper;

import com.cypcode.todo.entity.TodoEntity;
import com.cypcode.todo.model.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {

    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "priority", expression = "java(mapPriority(entity.getPriority()))")
    Todo toDto(TodoEntity entity);

    List<Todo> toDtoList(List<TodoEntity> entities);

    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "completed",   constant = "false")
    @Mapping(target = "createdAt",   ignore = true)
    @Mapping(target = "updatedAt",   ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "tags",        source = "tags")
    @Mapping(target = "priority",    expression = "java(mapEntityPriority(request.getPriority()))")
    TodoEntity toEntity(CreateTodoRequest request);

    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "completed",   ignore = true)
    @Mapping(target = "createdAt",   ignore = true)
    @Mapping(target = "updatedAt",   ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "tags",        source = "tags")
    @Mapping(target = "priority",    expression = "java(mapEntityPriority(request.getPriority()))")
    void updateEntity(UpdateTodoRequest request, @MappingTarget TodoEntity entity);

    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "completed",   ignore = true)
    @Mapping(target = "createdAt",   ignore = true)
    @Mapping(target = "updatedAt",   ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    void patchEntity(PatchTodoRequest request, @MappingTarget TodoEntity entity);

    default Priority mapPriority(TodoEntity.Priority p) {
        return p == null ? null : Priority.valueOf(p.name());
    }

    default TodoEntity.Priority mapEntityPriority(Priority p) {
        return p == null ? null : TodoEntity.Priority.valueOf(p.name());
    }
}
