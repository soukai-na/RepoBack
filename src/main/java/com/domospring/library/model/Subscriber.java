package com.domospring.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_subscriber;
    @Column(name="nom_subscriber")
    private String nom_subscriber;
    @Column(name="prenom_subscriber")
    private String prenom_subscriber;
    @Column(name="num_sim")
    private int num_sim;
    @Column(name="fonction")
    private String fonction;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date_creation;
    @PrePersist
    private void onCreate(){
        date_creation=new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    private Service service;
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Materiel> materiels;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="facture_id", referencedColumnName = "id_facture")
    private Facture facture;


    @ManyToOne
    @JoinColumn(name = "id_abonnement")
    private Abonnement abonnement;




}
