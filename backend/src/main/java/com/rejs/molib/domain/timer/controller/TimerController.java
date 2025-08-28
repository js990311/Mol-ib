package com.rejs.molib.domain.timer.controller;

import com.rejs.molib.domain.timer.dto.TimerCreateRequest;
import com.rejs.molib.domain.timer.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class TimerController {
    private final TimerService timerService;

    @PostMapping("/task/{id}/timer")
    public ResponseEntity<Void> getTimer(@PathVariable("id") Long id, @RequestBody TimerCreateRequest request){
        Long timerId = timerService.createTimer(id, request.getTime(), request.getNote());
        return ResponseEntity.created(URI.create("/api/task" + id)).build();
    }


}
