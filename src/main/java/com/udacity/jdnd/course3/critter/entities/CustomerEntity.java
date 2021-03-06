package com.udacity.jdnd.course3.critter.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Column(length = 20)
    private String phoneNumber;
    @Size(max = 200)
    private String notes;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PetEntity> pets = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", notes='").append(notes).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
