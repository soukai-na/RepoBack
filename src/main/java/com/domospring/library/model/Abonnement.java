package com.domospring.library.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

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

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Subscriber> subscribers;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Facture> factures;


}

