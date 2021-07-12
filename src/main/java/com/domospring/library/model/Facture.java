package com.domospring.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscriber_id",referencedColumnName = "id_subscriber")
    private Subscriber subscriber;


    public void updateFactureSubscriber(Subscriber subscriber) {
        this.subscriber=subscriber;
    }
}

