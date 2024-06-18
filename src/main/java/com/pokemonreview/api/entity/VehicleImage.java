package com.pokemonreview.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="vehicle_image")
public class VehicleImage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fileName;
        private String fileType;

        @Lob
        private byte[] data;

        private boolean isMainImage;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "vehicle_id" )
        @JsonBackReference
        private Vehicle vehicle;
    }


