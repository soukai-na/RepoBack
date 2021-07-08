package com.domospring.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@Setter
@Getter
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

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "service_id", referencedColumnName = "id_service")
    private Service service;

    public void updateServiceSubscriber(Service service) {
        this.service=service;
    }
    public void updateAbonnementSubscriber(Abonnement abonnement) {
        this.abonnement=abonnement;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "subscriber")
    private Set<Materiel> materiels=new HashSet<>();


    @OneToOne
    @JsonIgnore
    private Facture facture;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "abonnement_id",referencedColumnName = "id_abonnement")
    private Abonnement abonnement;



}
