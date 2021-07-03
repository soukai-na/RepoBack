package com.domospring.library.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@ToString
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
    @Column(name="date")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Historique> historiques;
    @ManyToOne
    @JoinColumn(name="id_sub")
    private Subscriber subscriber;




}
