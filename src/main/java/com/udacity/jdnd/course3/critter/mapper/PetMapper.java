package com.udacity.jdnd.course3.critter.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.udacity.jdnd.course3.critter.domain.PetDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "customer", ignore = true)
    PetEntity dtoToEntity(PetDTO petDTO);
    PetDTO entityToDto(PetEntity petEntity);

    @AfterMapping
    default void addRelationships(@MappingTarget PetDTO petDTO, PetEntity petEntity) {
        if(petEntity.getCustomer()!=null)
          petDTO.setOwnerId(petEntity.getCustomer().getId());
    }

    @AfterMapping
    default void addRelationships(@MappingTarget PetEntity petEntity, PetDTO petDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(petDTO.getOwnerId());
        petEntity.setCustomer(customerEntity);
    }
}
