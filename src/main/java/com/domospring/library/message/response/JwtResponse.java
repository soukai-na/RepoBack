package com.domospring.library.message.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String type="Bearer";
    private String username;
    private Collection authorities;

    public JwtResponse(String accessToken,String username,Collection authorities){
        this.token=accessToken;
        this.username=username;
        this.authorities=authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
