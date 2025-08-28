package com.rejs.molib.domain.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rejs.molib.domain.task.dto.TaskCompleteRequest;
import com.rejs.molib.domain.task.dto.TaskCreateRequest;
import com.rejs.molib.domain.task.dto.TaskDto;
import com.rejs.molib.domain.task.dto.TaskSummaryDto;
import com.rejs.molib.domain.task.service.TaskService;
import com.rejs.molib.domain.timer.dto.TimerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[TaskController] /api/tasks 테스트")
    void getTasks() throws Exception {
        // g
        Long id = 1L;
        boolean isComplete = true;
        String name = "name";
        Long focusedTime = 100L;

        List<TaskSummaryDto> tasks = Collections.singletonList(
            new TaskSummaryDto(id, name, isComplete, focusedTime)
        );
        when(taskService.readTasks()).thenReturn(tasks);

        // w
        // t

        mockMvc.perform(get("/api/task").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.data[0].id").value(id))
                .andExpect(jsonPath("$.data[0].name").value(name))
                .andExpect(jsonPath("$.data[0].focusedTime").value(focusedTime))
                .andExpect(jsonPath("$.data[0].complete").value(isComplete))
        ;
    }

    @Test
    void getTaskById() throws Exception {
        // g
        Long id = 1L;
        boolean isComplete = true;
        String name = "name";
        Integer time = 100;
        List<TimerDto> timers = Collections.singletonList(new TimerDto(id, time, name));

        TaskDto tasks = new TaskDto(id, name, isComplete, timers);
        when(taskService.readTaskById(id)).thenReturn(tasks);

        // w
        // t
        mockMvc.perform(get("/api/task/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.complete").value(isComplete))
                .andExpect(jsonPath("$.timers[0].id").value(id))
                .andExpect(jsonPath("$.timers[0].time").value(time))
                .andExpect(jsonPath("$.timers[0].note").value(name))
        ;

    }

    @Test
    void createTask() throws Exception {
        Long id = 1L;
        String name = "name";
        TaskCreateRequest request = TaskCreateRequest.builder()
                .name(name)
                .build();

        when(taskService.createTask(name)).thenReturn(1L);

        mockMvc.perform(post("/api/task").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/task/" + id))
        ;
    }

    @Test
    void updateTask() throws Exception{
        boolean isComplete = true;
        Long id = 1L;
        TaskCompleteRequest reqeust = TaskCompleteRequest
                .builder()
                .isComplete(isComplete)
                .build();

        mockMvc.perform(post("/api/task/{id}/complete", id).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reqeust)))
                .andExpect(status().isNoContent());
    }
}