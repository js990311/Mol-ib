package com.rejs.molib.domain.task.service;

import com.rejs.molib.domain.task.Task;
import com.rejs.molib.domain.task.dto.TaskDto;
import com.rejs.molib.domain.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    private Task task;
    private Long taskId;
    private String taskName = "testTask";

    @BeforeEach
    void setUp(){
        task = Task.builder()
                .id(taskId)
                .name(taskName)
                .isComplete(false)
                .build();
    }

    @Test
    @DisplayName("task 생성")
    void createTask() {
        // g
        given(taskRepository.save(any(Task.class))).willReturn(task);

        // w
        Long savedTaskId = taskService.createTask("testTask");

        // t
        assertEquals(taskId, savedTaskId);
    }

    @Test
    @DisplayName("ID로 task 조회 테스트")
    void readTaskById_Success() {
        // g
        given(taskRepository.findById(taskId)).willReturn(Optional.of(task));

        // w
        TaskDto taskDto = taskService.readTaskById(taskId);

        // t
        assertEquals(taskId, taskDto.getId());
        assertEquals(taskName, taskDto.getName());
    }

    @Test
    void updateTaskIsComplete() {
        // given
        given(taskRepository.findById(taskId)).willReturn(Optional.of(task));

        // when
        taskService.updateTaskIsComplete(taskId, true);

        // then
        assertTrue(task.isComplete());
    }
}