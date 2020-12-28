package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {

        return scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> findAll() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> getScheduleForPet(long petId) {
        return scheduleRepository.findByPetIds(petId);
    }

    public List<ScheduleEntity> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findByEmployeeIds(employeeId);
    }

    public List<ScheduleEntity> getScheduleForCustomer(long customerId) {
        CustomerEntity customer = customerService.findBy(customerId);
        List<Long> petIds = customer.getPetIds();
        List<ScheduleEntity> scheduleEntityList = scheduleRepository.findByPetIdsIn(petIds);
        return scheduleEntityList;
    }
}
