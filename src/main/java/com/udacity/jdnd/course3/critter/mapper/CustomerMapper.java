package com.udacity.jdnd.course3.critter.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.udacity.jdnd.course3.critter.domain.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "pets", ignore = true)
    CustomerEntity dtoToEntity(CustomerDTO customerDTO);
    CustomerDTO etityToDto(CustomerEntity customerEntity);

    @AfterMapping
    default void addRelationships(@MappingTarget CustomerDTO customerDTO, CustomerEntity customerEntity) {
        List<Long> petIds = customerEntity.getPets().stream().map(PetEntity::getId).collect(Collectors.toList());
        customerDTO.setPetIds(petIds);
    }

    @AfterMapping
    default void addRelationships(@MappingTarget CustomerEntity customerEntity, CustomerDTO customerDTO) {
       List<PetEntity> petEntityList = new ArrayList<>();
        for(long i:customerDTO.getPetIds()){
            PetEntity petEntity = new PetEntity();
            petEntity.setId(i);
            petEntityList.add(petEntity);
        }



        customerEntity.setPets(petEntityList);
    }
}
