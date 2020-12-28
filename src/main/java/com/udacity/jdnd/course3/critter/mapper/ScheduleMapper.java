package com.udacity.jdnd.course3.critter.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.udacity.jdnd.course3.critter.domain.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "pets", ignore = true)
    ScheduleEntity dtoToEntity(ScheduleDTO scheduleDTO);

    @Mapping(target = "employeeIds", ignore = true)
    @Mapping(target = "petIds", ignore = true)
    ScheduleDTO entityToDto(ScheduleEntity scheduleEntity);


    @AfterMapping
    default void addRelationships(@MappingTarget ScheduleDTO scheduleDTO, ScheduleEntity scheduleEntity) {
        List<Long> employeeIds  = scheduleEntity.getEmployees().stream().map(EmployeeEntity::getId).collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(employeeIds);

        List<Long> petIds  = scheduleEntity.getPets().stream().map(PetEntity::getId).collect(Collectors.toList());
        scheduleDTO.setPetIds(petIds);
    }

    @AfterMapping
    default void addRelationships(@MappingTarget ScheduleEntity scheduleEntity,ScheduleDTO scheduleDTO) {
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        for(Long id: scheduleDTO.getEmployeeIds()){
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setId(id);
            employeeEntityList.add(employeeEntity);
        }

        List<PetEntity> petEntityList= new ArrayList<>();
        for(Long id: scheduleDTO.getPetIds()){
            PetEntity petEntity = new PetEntity();
            petEntity.setId(id);
            petEntityList.add(petEntity);
        }

        scheduleEntity.setEmployees(employeeEntityList);
        scheduleEntity.setPets(petEntityList);

    }
}
