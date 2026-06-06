package com.cypcode.todo.repository;

import com.cypcode.todo.entity.TodoEntity;
import com.cypcode.todo.entity.TodoEntity.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    @Query("""
            SELECT t FROM TodoEntity t
            WHERE (:completed IS NULL OR t.completed = :completed)
              AND (:priority   IS NULL OR t.priority = :priority)
              AND (:tag        IS NULL OR :tag MEMBER OF t.tags)
              AND (:searchPat  IS NULL
                   OR LOWER(t.title) LIKE :searchPat
                   OR LOWER(COALESCE(t.description, '')) LIKE :searchPat)
            """)
    Page<TodoEntity> findFiltered(
            @Param("completed")  Boolean completed,
            @Param("priority")   Priority priority,
            @Param("tag")        String tag,
            @Param("searchPat")  String searchPat,
            Pageable pageable
    );

    long countByCompleted(boolean completed);

    @Query("SELECT COUNT(t) FROM TodoEntity t WHERE t.completed = false AND t.dueDate < :today")
    long countOverdue(@Param("today") LocalDate today);

    @Query("SELECT COUNT(t) FROM TodoEntity t WHERE t.completed = false AND t.priority = :priority")
    long countByPriorityAndNotCompleted(@Param("priority") Priority priority);

    @Query("SELECT DISTINCT tag FROM TodoEntity t JOIN t.tags tag ORDER BY tag")
    List<String> findAllDistinctTags();

    @Query("SELECT t FROM TodoEntity t WHERE t.id IN :ids")
    List<TodoEntity> findAllByIdIn(@Param("ids") List<Long> ids);
}
