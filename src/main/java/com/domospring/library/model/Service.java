package com.domospring.library.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@ToString
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_service;
    @Column(name="nom_service")
    private String nom_service;
    @Column(name="description")
    private String description;

    @OneToMany
    private Collection<Subscriber> subscribers;



}
