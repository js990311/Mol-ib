package com.rejs.molib.domain.task.repository;

import com.rejs.molib.domain.task.Task;
import com.rejs.molib.domain.task.dto.TaskSummaryDto;
import com.rejs.molib.domain.timer.Timer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    private Task task1;
    private Task task2;

    @Test
    @DisplayName("taskSummaries 조회 테스트")
    void findTaskSummariesTest(){
        // g
        task1 = Task.builder()
                .name("testname1")
                .isComplete(false)
                .build();

        task2 = Task.builder()
                .name("testname2")
                .isComplete(false)
                .build();

        Timer timer1 = new Timer("timer1", 100, task1);
        Timer timer2 = new Timer("timer2", 200, task1);
        Timer timer3 = new Timer("timer3", 400, task2);

        taskRepository.save(task1);
        taskRepository.save(task2);

        // w 
        List<TaskSummaryDto> taskSummaries = taskRepository.findTaskSummaries();

        // t
        assertNotNull(taskSummaries);
        assertEquals(2, taskSummaries.size());

        assertEquals(300L, taskSummaries.get(0).getFocusedTime());
        assertEquals(400L,  taskSummaries.get(1).getFocusedTime());
    }

}