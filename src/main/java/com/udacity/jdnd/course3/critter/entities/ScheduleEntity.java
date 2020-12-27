package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @ElementCollection
    @CollectionTable(name = "schedule_employee_id")
    private List<Long> employeeIds;
    @ElementCollection
    @CollectionTable(name = "schedule_pet_id")
    private List<Long> petIds;
    private LocalDate date;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<EmployeeSkill> activities;

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
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
        sb.append(", employeeIds=").append(employeeIds);
        sb.append(", petIds=").append(petIds);
        sb.append(", date=").append(date);
        sb.append(", activities=").append(activities);
        sb.append('}');
        return sb.toString();
    }
}
