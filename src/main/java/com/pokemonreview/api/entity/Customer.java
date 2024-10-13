package com.pokemonreview.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String address;
    private double discountOffered;
    private String paymentMethod;
    private String paymentType;
    private String mobileNo;
    private String selledPrice;
    private String invoicedBy;

    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    // Foreign key reference to Vehicle
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;
}
