package com.rejs.molib.domain.timer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rejs.molib.domain.timer.dto.TimerCreateRequest;
import com.rejs.molib.domain.timer.service.TimerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimerController.class)
class TimerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TimerService timerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTimer() throws Exception{
        Long id = 1L;
        Integer time = 100;
        String note = "note";
        TimerCreateRequest request = TimerCreateRequest.builder()
                .time(time)
                .note(note)
                .build();

        when(timerService.createTimer(id, time, note)).thenReturn(id);

        mockMvc.perform(post("/api/task/{id}/timer", id).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","/api/task" + id))
        ;

    }
}