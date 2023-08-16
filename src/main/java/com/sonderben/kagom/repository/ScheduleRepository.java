package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.Role;
import com.sonderben.kagom.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
