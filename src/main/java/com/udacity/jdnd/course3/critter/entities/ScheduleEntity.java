package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.udacity.jdnd.course3.critter.domain.EmployeeSkill;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(targetEntity = EmployeeEntity.class)
    private List<EmployeeEntity> employees = new ArrayList<>();

    @ManyToMany(targetEntity = PetEntity.class)
    private List<PetEntity> pets = new ArrayList<>();

    private LocalDate date;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<EmployeeSkill> activities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScheduleEntity{");
        sb.append("id=").append(id);
        sb.append(", employees=").append(employees);
        sb.append(", pets=").append(pets);
        sb.append(", date=").append(date);
        sb.append(", activities=").append(activities);
        sb.append('}');
        return sb.toString();
    }
}
