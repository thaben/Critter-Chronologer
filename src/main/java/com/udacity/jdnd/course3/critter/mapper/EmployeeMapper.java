package com.udacity.jdnd.course3.critter.mapper;

import org.mapstruct.Mapper;

import com.udacity.jdnd.course3.critter.domain.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeEntity dtoToEntity(EmployeeDTO employeeDTO);

    EmployeeDTO entityToDto(EmployeeEntity employeeEntity);


}
