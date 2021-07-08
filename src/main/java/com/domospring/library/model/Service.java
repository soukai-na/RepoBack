package com.domospring.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@Setter
@Getter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_service;
    @Column(name="nom_service")
    private String nom_service;
    @Column(name="description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<Subscriber> subscribers= new HashSet<>();



}
