package com.pokemonreview.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="advertiesment_status")
public class AdvertiesmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "email_sent_flag")
    private boolean emailSentFlag;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonBackReference
    @JoinColumn(name = "advertiesment_id")
    private Advertiesment advertiesment;
}
