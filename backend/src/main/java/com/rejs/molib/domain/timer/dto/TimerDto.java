package com.rejs.molib.domain.timer.dto;

import com.rejs.molib.domain.timer.Timer;
import lombok.Getter;

@Getter
public class TimerDto {
    private Long id;
    private Integer time;
    private String note;

    public TimerDto(Long id, Integer time, String note) {
        this.id = id;
        this.time = time;
        this.note = note;
    }

    public static TimerDto of(Timer timer){
        return new TimerDto(timer.getId(), timer.getTime(), timer.getNote());
    }
}
