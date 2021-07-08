package com.domospring.library.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString

public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_abonnement;
    @Column(name="type")
    private String type;
    @Column(name="tarif")
    private String tarif;
    @Column(name="type_forfait")
    private String type_forfait;

    @JsonIgnore
    @OneToMany(mappedBy = "abonnement")
    private Set<Subscriber> subscribers = new HashSet<>();




}

