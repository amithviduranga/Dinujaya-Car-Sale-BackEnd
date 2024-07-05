package com.pokemonreview.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="advertiesment")
public class Advertiesment {
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

    @Column(name = "category")
    private String category;

    @Column(name = "vehicle_condition") // Changed to vehicle_condition
    private String vehicle_Condition;

    @Column(name = "registered_year")
    private String registeredYear;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "modified_on")
    private Date modifiedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name ="location")
    private String location;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "payment")
    private String payment;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "advertiesment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<AdvertiesmentImage> images = new HashSet<>();
}
