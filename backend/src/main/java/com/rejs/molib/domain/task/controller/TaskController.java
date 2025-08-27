package com.rejs.molib.domain.task.controller;

import com.rejs.molib.domain.task.dto.TaskCompleteRequest;
import com.rejs.molib.domain.task.dto.TaskCreateRequest;
import com.rejs.molib.domain.task.dto.TaskDto;
import com.rejs.molib.domain.task.dto.TaskSummaryDto;
import com.rejs.molib.domain.task.service.TaskService;
import com.rejs.molib.grobal.response.ListResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/task")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    /**
     * 전체 Task 반환
     * @return
     */
    @GetMapping
    public ListResponse<TaskSummaryDto> getTasks(){
        List<TaskSummaryDto> taskSummaryDtos = taskService.readTasks();
        return ListResponse.of(taskSummaryDtos);
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long id){
        TaskDto taskDto = taskService.readTaskById(id);
        return taskDto;
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateRequest request){
        Long taskId = taskService.createTask(request.getName());
        return ResponseEntity.created(URI.create("/api/task/" + taskId)).build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskCompleteRequest request){
        taskService.updateTaskIsComplete(id, request.isComplete());
        return ResponseEntity.noContent().build();
    }
}
