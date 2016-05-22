package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.interfaces.Observer;

import java.util.Date;


/**
 * Created by taibic on 14/04/16.
 */
public abstract class Employee implements Observer{

    private int image;
    private Long id;
    private String name;
    private Integer age;
    private Date startDate;
    private Date endDate;
    private Double salary;
    private String profession;

    public Employee() {
    }

    public Employee(int image, String name, Integer age, Date startDate, Date endDate
            , Double salary, String profession) {
        this.image = image;
        this.profession = profession;
        this.name = name;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profression) {
        this.profession = profression;
    }
}
