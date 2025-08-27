package com.rejs.molib.domain.task.repository;

import com.rejs.molib.domain.task.Task;
import com.rejs.molib.domain.task.dto.TaskSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select new com.rejs.molib.domain.task.dto.TaskSummaryDto(task.id, task.name, task.isComplete, sum(timers.time)) from Task task join task.timers timers group by task.id, task.name")
    List<TaskSummaryDto> findTaskSummaries();
}
