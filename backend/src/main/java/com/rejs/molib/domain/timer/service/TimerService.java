package com.rejs.molib.domain.timer.service;

import com.rejs.molib.domain.task.Task;
import com.rejs.molib.domain.task.repository.TaskRepository;
import com.rejs.molib.domain.timer.Timer;
import com.rejs.molib.domain.timer.repository.TimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimerService {
    private final TimerRepository timerRepository;
    private final TaskRepository taskRepository;

    /* CREATE */
    @Transactional
    public Long createTimer(Long taskId, Integer time, String note){
        Task task = taskRepository.findById(taskId).orElseThrow();
        Timer timer = new Timer(note, time, task);
        return timerRepository.save(timer).getId();
    }

    /* READ */

    /* UPDATE */

    /* DELETE */

}
