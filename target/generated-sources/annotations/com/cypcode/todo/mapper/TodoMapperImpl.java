package com.cypcode.todo.mapper;

import com.cypcode.todo.entity.TodoEntity;
import com.cypcode.todo.model.CreateTodoRequest;
import com.cypcode.todo.model.PatchTodoRequest;
import com.cypcode.todo.model.Todo;
import com.cypcode.todo.model.UpdateTodoRequest;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-06T11:21:41+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo toDto(TodoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Todo todo = new Todo();

        Set<String> set = entity.getTags();
        if ( set != null ) {
            todo.setTags( new LinkedHashSet<String>( set ) );
        }
        todo.setId( entity.getId() );
        todo.setTitle( entity.getTitle() );
        todo.setDescription( entity.getDescription() );
        todo.setCompleted( entity.isCompleted() );
        todo.setDueDate( entity.getDueDate() );
        todo.setCreatedAt( entity.getCreatedAt() );
        todo.setUpdatedAt( entity.getUpdatedAt() );
        todo.setCompletedAt( entity.getCompletedAt() );

        todo.setPriority( mapPriority(entity.getPriority()) );

        return todo;
    }

    @Override
    public List<Todo> toDtoList(List<TodoEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Todo> list = new ArrayList<Todo>( entities.size() );
        for ( TodoEntity todoEntity : entities ) {
            list.add( toDto( todoEntity ) );
        }

        return list;
    }

    @Override
    public TodoEntity toEntity(CreateTodoRequest request) {
        if ( request == null ) {
            return null;
        }

        TodoEntity.TodoEntityBuilder todoEntity = TodoEntity.builder();

        Set<String> set = request.getTags();
        if ( set != null ) {
            todoEntity.tags( new LinkedHashSet<String>( set ) );
        }
        todoEntity.title( request.getTitle() );
        todoEntity.description( request.getDescription() );
        todoEntity.dueDate( request.getDueDate() );

        todoEntity.completed( false );
        todoEntity.priority( mapEntityPriority(request.getPriority()) );

        return todoEntity.build();
    }

    @Override
    public void updateEntity(UpdateTodoRequest request, TodoEntity entity) {
        if ( request == null ) {
            return;
        }

        if ( entity.getTags() != null ) {
            Set<String> set = request.getTags();
            if ( set != null ) {
                entity.getTags().clear();
                entity.getTags().addAll( set );
            }
            else {
                entity.setTags( null );
            }
        }
        else {
            Set<String> set = request.getTags();
            if ( set != null ) {
                entity.setTags( new LinkedHashSet<String>( set ) );
            }
        }
        entity.setTitle( request.getTitle() );
        entity.setDescription( request.getDescription() );
        entity.setDueDate( request.getDueDate() );

        entity.setPriority( mapEntityPriority(request.getPriority()) );
    }

    @Override
    public void patchEntity(PatchTodoRequest request, TodoEntity entity) {
        if ( request == null ) {
            return;
        }

        entity.setTitle( request.getTitle() );
        entity.setDescription( request.getDescription() );
        entity.setPriority( mapEntityPriority( request.getPriority() ) );
        entity.setDueDate( request.getDueDate() );
        if ( entity.getTags() != null ) {
            Set<String> set = request.getTags();
            if ( set != null ) {
                entity.getTags().clear();
                entity.getTags().addAll( set );
            }
            else {
                entity.setTags( null );
            }
        }
        else {
            Set<String> set = request.getTags();
            if ( set != null ) {
                entity.setTags( new LinkedHashSet<String>( set ) );
            }
        }
    }
}
