package com.udacity.jdnd.course3.critter.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {
        LOGGER.info("Creating a schedule");

        List<Long> petIds = scheduleEntity.getPets().stream().map(PetEntity::getId).collect(Collectors.toList());
        List<Long> employeeIds = scheduleEntity.getEmployees().stream().map(EmployeeEntity::getId).collect(Collectors.toList());

        scheduleEntity.setEmployees(employeeService.findAllByIds(employeeIds));
        scheduleEntity.setPets( petService.findAllByIds(petIds));

        return scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> findAll() {

        List<ScheduleEntity> list = scheduleRepository.findAll();
        LOGGER.info("findAll() List<ScheduleEntity> {} ",list);
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> getScheduleForPet(long petId) {
        return scheduleRepository.findByPets_Id(petId);
    }

    public List<ScheduleEntity> getScheduleForEmployee(long employeeId) {
        EmployeeEntity employeeEntity = employeeService.findById(employeeId);
        return scheduleRepository.getAllByEmployeesContains(employeeEntity);
    }

    public List<ScheduleEntity> getScheduleForCustomer(long customerId) {
        CustomerEntity customerEntity = customerService.findBy(customerId);
        return  scheduleRepository.getAllByPetsIn(customerEntity.getPets());
    }
}
