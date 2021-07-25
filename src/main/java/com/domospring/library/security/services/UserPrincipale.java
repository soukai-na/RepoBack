package com.domospring.library.security.services;

import com.domospring.library.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class UserPrincipale implements UserDetails {
    private static final long serialVersionUID=1L;
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String username;

    @JsonIgnore
    private String password;

    private Collection authorities;

    public UserPrincipale(Long id, String nom, String prenom,String email, String username, String password, Collection authorities) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.username=username;
        this.password=password;
        this.authorities=authorities;
    }

    public static UserPrincipale build(User user){
        List authorities=user.getRoles().stream().map(role->
                new SimpleGrantedAuthority(role.getName().name())
                ).collect(Collectors.toList());
        return new UserPrincipale(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null || getClass()!=o.getClass())return false;
        UserPrincipale user= (UserPrincipale) o;
        return Objects.equals(id,user.id);
    }
}
