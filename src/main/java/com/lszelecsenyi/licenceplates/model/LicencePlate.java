package com.lszelecsenyi.licenceplates.model;

import javax.persistence.*;

@Entity
@Table(name= "licence_plates")
public class LicencePlate {


    @Id
    private String plate;
    private String carBrand;
    private String carModel;
    private String color;
    private String year;

    public LicencePlate() {

    }

    public LicencePlate(String plate, String carBrand, String carModel, String color, String year) {
        this.plate = plate;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.color = color;
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
