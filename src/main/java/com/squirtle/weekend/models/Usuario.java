//package com.squirtle.weekend.models;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.repository.cdi.Eager;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToMany;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Getter @Setter
//public class Usuario implements UserDetails {
//    private Long id;
//    private String senha;
//    private String email;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Role> roles = new ArrayList<>();
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public String getPassword() {
//        return senha;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
