package com.udacity.jdnd.course3.critter.mapper;

import org.mapstruct.Mapper;

import com.udacity.jdnd.course3.critter.domain.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleEntity dtoToEntity(ScheduleDTO scheduleDTO);
    ScheduleDTO entityToDto(ScheduleEntity scheduleEntity);
}
