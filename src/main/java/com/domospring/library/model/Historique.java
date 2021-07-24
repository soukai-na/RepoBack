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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date_action;
    @PrePersist
    private void onCreate(){
        date_action=new Date();
    }

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="materiel_id", referencedColumnName = "id_materiel")
    private Materiel materiel;

}
