package com.domospring.library.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_action;
    @Column(name="justif")
    private String justif;
    @Column(name="date_action")
    private Date date_action;
    @Column(name="description")
    private  String description;

    @ManyToOne
    @JoinColumn(name = "id_materiel")
    private Materiel materiel;


}
