package com.udacity.jdnd.course3.critter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
@Transactional
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PetRepository petRepository;

    public CustomerEntity save(CustomerEntity customerEntity){

        List<PetEntity> petEntityList = new ArrayList<>();
        for(long id:customerEntity.getPets().stream().map(PetEntity::getId).collect(Collectors.toList())){
            petEntityList.add(petRepository.findById(id).orElse(null));
        }
        customerEntity.setPets(petEntityList);
        return customerRepository.save(customerEntity);
    }

    public CustomerEntity findBy(long id){
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    public List<CustomerEntity> findAll(){
        return customerRepository.findAll();
    }

    public CustomerEntity getOwnerByPet(Long petId){
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(()-> new RuntimeException());
        return customerRepository.findById(petEntity.getCustomer().getId()).orElseThrow(()->new RuntimeException());
    }

    public void addPet(PetEntity petEntity){
        LOGGER.info("Adding PetEntity To Customer {}",petEntity);
        CustomerEntity customerEntity =  customerRepository.findById(petEntity.getCustomer().getId()).orElseThrow(()->new RuntimeException());
        customerEntity.getPets().add(petEntity);
        customerRepository.save(customerEntity);
    }
}
