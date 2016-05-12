package com.br.smartzoo.model.entity;

import java.util.Date;

/**
 * Created by taibic on 14/04/16.
 */
public abstract class Employee {

    private Long id;
    private String name;
    private Integer age;
    private String cpf;
    private Date startDate;
    private Date endDate;
    private Double salary;

    public Employee() {
    }

    public Employee(String name, Integer age, String cpf, Date startDate, Date endDate, Double salary) {
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public abstract Double calculateSalary();




}
