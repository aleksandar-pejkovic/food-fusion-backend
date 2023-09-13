package dev.alpey.foodfusionbackend.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {

    USER,
    ADMIN,
    MASTER_ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }

}
