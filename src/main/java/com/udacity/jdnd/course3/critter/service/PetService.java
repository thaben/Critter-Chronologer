package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
public class PetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetMapper petMapper;

    public List<PetEntity> findAll() {
        return petRepository.findAll();
    }

    public PetEntity findByID(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public PetEntity savePet(PetEntity petEntity) {
        LOGGER.info("Pet entity going to be saved is {}", petEntity);

        PetEntity saved = petRepository.save(petEntity);

        if (petEntity.getOwnerId() != 0) {
            customerService.addPet(saved.getOwnerId(), saved.getId());
        }

        return saved;
    }

    public List<PetEntity> findByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);

    }
}
