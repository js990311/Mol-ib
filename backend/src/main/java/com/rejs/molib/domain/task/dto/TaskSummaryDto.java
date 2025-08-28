package com.rejs.molib.domain.task.dto;

import com.rejs.molib.domain.task.Task;
import lombok.Getter;

@Getter
public class TaskSummaryDto {
    private Long id;
    private String name;
    private Long focusedTime;
    private boolean isComplete;


    public TaskSummaryDto(Long id, String name, boolean isComplete, Long focusedTime) {
        this.id = id;
        this.name = name;
        this.isComplete = isComplete;
        this.focusedTime = focusedTime;
    }
}
