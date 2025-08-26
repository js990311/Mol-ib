package com.rejs.molib.domain.timer.repository;

import com.rejs.molib.domain.timer.Timer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerRepository extends JpaRepository<Timer, Long> {
}
