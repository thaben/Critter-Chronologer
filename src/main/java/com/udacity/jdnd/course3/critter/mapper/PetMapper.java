package com.udacity.jdnd.course3.critter.mapper;

import org.mapstruct.Mapper;

import com.udacity.jdnd.course3.critter.domain.PetDTO;
import com.udacity.jdnd.course3.critter.entities.PetEntity;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetEntity dtoToEntity(PetDTO petDTO);
    PetDTO entityToDto(PetEntity petEntity);
}
