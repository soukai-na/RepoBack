package com.domospring.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name="User")
public class User extends Profil{
    @Column(name="nom_user")
    private String nom_user;
    @Column(name="prenom_user")
    private String prenom_user;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;


}
