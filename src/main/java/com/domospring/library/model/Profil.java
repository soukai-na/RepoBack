package com.domospring.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@ToString
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profil;
    @Column(name="designation")
    private String designation;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
}
