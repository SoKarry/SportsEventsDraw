package com.sp.SportsEventsDraw.domain;

import org.springframework.security.core.GrantedAuthority;

//Определяет доустпные роли разного уровня для пользователя
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
