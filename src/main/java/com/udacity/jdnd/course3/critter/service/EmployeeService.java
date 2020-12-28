package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.domain.EmployeeDTO;
import com.udacity.jdnd.course3.critter.domain.EmployeeSkill;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity findById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException());
        employeeEntity.setDaysAvailable(daysAvailable);
        employeeRepository.save(employeeEntity);
    }

    public List<EmployeeEntity> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        List<EmployeeEntity> employees = employeeRepository
                .findByDaysAvailable(date.getDayOfWeek()).stream()
                .filter(e -> e.getSkills().containsAll(skills))
                .collect(Collectors.toList());
        return employees;
    }
}
