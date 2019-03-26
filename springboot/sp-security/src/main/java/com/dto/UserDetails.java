package com.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by shejiewei on 2019/3/26.
 */
public interface UserDetails extends Serializable{

    Collection<? extends GrantedAuthority> getAuthrities();
    String getPassword();
    String getUserName();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonexpired();
    boolean isEnabled();
}
