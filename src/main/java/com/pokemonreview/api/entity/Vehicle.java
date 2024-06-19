package com.pokemonreview.api.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "color")
    private String color;

    @Column(name = "reg_number")
    private String registrationNumber;

    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "vehicle_condition") // Changed to vehicle_condition
    private String vehicleCondition;

    @Column(name = "description")
    private String description;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "price")
    private Double price;
    @Column(name = "created_on")

    private Date createdOn;

    @Column(name = "modified_on")

    private Date modifiedOn;

    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String modifiedBy;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<VehicleSparePartRecommendation> recommendations = new HashSet<>();

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<VehicleImage> images = new HashSet<>();

}