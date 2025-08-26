package com.rejs.molib.domain.task.dto;

import com.rejs.molib.domain.task.Task;
import com.rejs.molib.domain.timer.dto.TimerDto;

import java.util.List;

public class TaskDto {
    private Long id;
    private String name;
    private boolean isComplete;
    private List<TimerDto> timers;

    public TaskDto(Long id, String name, boolean isComplete, List<TimerDto> timers) {
        this.id = id;
        this.name = name;
        this.isComplete = isComplete;
        this.timers = timers;
    }

     public static TaskDto of(Task task){
        return new TaskDto(
                task.getId(),
                task.getName(),
                task.isComplete(),
                task.getTimers().stream().map(TimerDto::of).toList()
        );
     }
}
