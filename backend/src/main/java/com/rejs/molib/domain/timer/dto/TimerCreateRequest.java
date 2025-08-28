package com.rejs.molib.domain.timer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TimerCreateRequest {
    private Integer time;
    private String note;
}
