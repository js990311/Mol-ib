package com.rejs.molib.domain.timer;

import com.rejs.molib.domain.task.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "timers")
public class Timer {
    @Id
    @GeneratedValue
    @Column(name = "timer_id")
    private Long id;

    @Column
    private String note;

    /* 관계 task */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "task_id", insertable = false, updatable = false)
    private Long taskId;

    public void mapTask(Task task){
        this.task = task;
        task.addTimer(this);
    }

    /* 생성 */

    public Timer(String note, Task task) {
        this.note = note;
        mapTask(task);
    }
}
