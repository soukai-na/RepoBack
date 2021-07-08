package com.domospring.library.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@ToString
@Getter
@Setter
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materiel;
    @Column(name = "serial_number")
    private Long serial_number;
    @Column(name = "nom")
    private String nom;
    @Column(name="model")
    private String model;
    @Column(name="description")
    private String description;
    @Column(name="type")
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date_ajout;
    @PrePersist
    private void onCreate(){
        date_ajout=new Date();
    }
    public void updateSubscriberMateriel(Subscriber subscriber) {
        this.subscriber=subscriber;
    }
   /*
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Historique> historiques;
    */

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="subscriber_id", referencedColumnName = "id_subscriber")
    private Subscriber subscriber;



}
