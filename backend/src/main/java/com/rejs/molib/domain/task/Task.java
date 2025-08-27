package com.rejs.molib.domain.task;

import com.rejs.molib.domain.timer.Timer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    @Column
    private String name;

    @Column
    private boolean isComplete;

    /* 관계 - Timer */
    @Builder.Default
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Timer> timers = new ArrayList<>();

    /**
     * 반드시 Timer의 mapTask를 통해서만 사용할 것
     * @param timer
     */
    public void addTimer(Timer timer){
        this.timers.add(timer);
    }

    /* 생성 */

    public Task(String name) {
        this.name = name;
    }

    /* 로직 */
    public void updateComplete(boolean isComplete){
        this.isComplete = isComplete;
    }
}
