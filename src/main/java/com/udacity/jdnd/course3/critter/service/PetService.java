package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
@Transactional
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

    public List<PetEntity> findAllByIds(List<Long> ids) {
        return petRepository.findAllById(ids);
    }

    public PetEntity savePet(PetEntity petEntity) {


        if (petEntity.getCustomer() != null) {
            CustomerEntity customerEntity = customerService.findBy(petEntity.getCustomer().getId());
            LOGGER.info("CustomerEntity {}", customerEntity);
            petEntity.setCustomer(customerEntity);
        }

        LOGGER.info("Pet entity going to be saved is {}", petEntity);
        PetEntity saved = petRepository.save(petEntity);
        customerService.addPet(saved);
        return saved;
    }

    public List<PetEntity> findByOwnerId(Long ownerId) {
        return petRepository.findByCustomer_Id(ownerId);

    }
}
