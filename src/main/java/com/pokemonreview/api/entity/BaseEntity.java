package com.pokemonreview.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.*;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Setter
@Getter

public class BaseEntity {

    @Column(name = "created_on")
    @JsonFormat(shape = Shape.STRING,
                pattern = "yyyy-MM-dd")
    private String createdOn;

    @Column(name = "modified_on")
    @JsonFormat(shape = Shape.STRING,
            pattern = "yyyy-MM-dd")
    private String modifiedOn;

    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "created_by")
    private Long modifiedBy;


}
