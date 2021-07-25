package com.domospring.library.message.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignUpForm {
    @NotBlank
    @Size(min=2,max=50)
    private String nom;

    @NotBlank
    @Size(min=2,max=50)
    private String prenom;

    @NotBlank
    @Size(min=2,max=50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public Set getRole() {
        return this.role;
    }

    public void setRole(Set role) {
        this.role = role;
    }
}
