package com.udacity.jdnd.course3.critter.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.domain.PetDTO;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.service.PetService;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetMapper petMapper;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetEntity petEntity  = petMapper.dtoToEntity(petDTO);
        return petMapper.entityToDto(petService.savePet(petEntity));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
       return petMapper.entityToDto(petService.findByID(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.findAll().stream().map(petMapper::entityToDto).collect(Collectors.toList());
}

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.findByOwnerId(ownerId).stream().map(petMapper::entityToDto).collect(Collectors.toList());
    }
}
