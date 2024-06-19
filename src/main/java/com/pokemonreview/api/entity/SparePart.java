package com.pokemonreview.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spare_part")
@Data
@NoArgsConstructor
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "vehical_model")
    private String vehicleModel;
    @Column(name = "vehicle_brand")
    private String vehicleBrand;
    @Column(name = "available_qty")
    private int availableQty;
    @Column(name = "price")
    private double price;
    @Column(name = "part_name")
    private String partName;
    @Column(name = "description")
    private String description;
    @Column(name = "created_on")

    private Date createdOn;

    @Column(name = "modified_on")

    private Date modifiedOn;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String modifiedBy;

    @OneToMany(mappedBy = "sparePart", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<VehicleSparePartRecommendation> recommendations = new HashSet<>();
}
