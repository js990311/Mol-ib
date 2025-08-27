package com.rejs.molib.domain.task.service;

import com.rejs.molib.domain.task.Task;
import com.rejs.molib.domain.task.dto.TaskDto;
import com.rejs.molib.domain.task.dto.TaskSummaryDto;
import com.rejs.molib.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    /* CREATE */
    @Transactional
    public Long createTask(String name){
        Task task = new Task(name);
        return taskRepository.save(task).getId();
    }

    /* READ */
    public TaskDto readTaskById(Long id){
        Task task = taskRepository.findById(id).orElseThrow();
        return TaskDto.of(task);
    }

    public List<TaskSummaryDto> readTasks(){
        return taskRepository.findTaskSummaries();
    }


    /* UPDATE */
    @Transactional
    public void updateTaskIsComplete(Long id, boolean isCompelete){
        Task task = taskRepository.findById(id).orElseThrow();
        task.updateComplete(isCompelete);
    }

    /* DELETE */

}
