package com.domospring.library.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString

public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_facture;
    @Column(name="num_facture")
    private Long num_facture;
    @Column(name="date_facture")
    private Date date_facture;
    @Column(name="Montant")
    private Long montant;

    @OneToOne(mappedBy = "facture",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Subscriber subscriber;
/*
    @ManyToOne
    @JoinColumn(name = "id_abonnement")
    private Abonnement abonnement;
*/
}

