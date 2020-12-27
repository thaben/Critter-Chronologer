package com.udacity.jdnd.course3.critter.mapper;

import org.mapstruct.Mapper;

import com.udacity.jdnd.course3.critter.domain.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity dtoToEntity(CustomerDTO customerDTO);
    CustomerDTO etityToDto(CustomerEntity customerEntity);
}
