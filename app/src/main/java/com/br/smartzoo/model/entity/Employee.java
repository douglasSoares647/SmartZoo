package com.br.smartzoo.model.entity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.interfaces.Observer;

import java.util.Date;


/**
 * Created by taibic on 14/04/16.
 */
public abstract class Employee implements Observer{

    private String image;
    private Long id;
    private String name;
    private Integer age;
    private Date startDate;
    private Date endDate;
    private Double salary;
    private String profession;
    private Double price;
    private String status;
    private Integer stamina;

    public Employee() {
    }

    public Employee(String image, String name, Integer age, Date startDate, Date endDate
            , Double salary, String profession, String status) {
        this.image = image;
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profression) {
        this.profession = profression;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }


    public enum EmployeeEnum {

        Veterinary(R.drawable.ic_veterinary, 1000D, R.string.name_veterinary),
        Janitor(R.drawable.ic_janitor, 1000D,R.string.name_janitor),
        Feeder(R.drawable.ic_feeder, 1600D, R.string.name_feeder);

        private  int profession;
        private int image;
        private Double price;

        EmployeeEnum(int image, Double price, int profession) {
            this.image = image;
            this.price = price;
            this.profession = profession;
        }

        public int getImage() {
            return image;
        }

        public Double getPrice() {
            return price;
        }

        public int getProfession() {
            return profession;
        }
    }

}
