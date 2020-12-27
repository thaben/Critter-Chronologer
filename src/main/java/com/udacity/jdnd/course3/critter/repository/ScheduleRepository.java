package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByPetIds(long petId);
    List<ScheduleEntity> findByEmployeeIds(long employeeId);
    List<ScheduleEntity> findByPetIdsIn(List<Long> petIds);

}
