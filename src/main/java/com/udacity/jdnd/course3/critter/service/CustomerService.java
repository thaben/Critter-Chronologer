package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PetRepository petRepository;

    public CustomerEntity save(CustomerEntity customerEntity){
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
        return customerRepository.findById(petEntity.getOwnerId()).orElseThrow(()->new RuntimeException());
    }

    public void addPet(long customerId, long petId){
       CustomerEntity customerEntity =  customerRepository.findById(customerId).orElseThrow(()->new RuntimeException());
        customerEntity.getPetIds().add(petId);
        customerRepository.save(customerEntity);
    }
}
