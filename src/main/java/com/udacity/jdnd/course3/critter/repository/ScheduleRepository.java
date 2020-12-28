package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByPets_Id(long petId);
    List<ScheduleEntity> findByEmployees_Id(long employeeId);
    List<ScheduleEntity> findByPets_IdIn(List<Long> petIds);
    List<ScheduleEntity> getAllByPetsIn(List<PetEntity> pets);
    List<ScheduleEntity> getAllByEmployeesContains(EmployeeEntity employee);

}
