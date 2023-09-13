package dev.alpey.foodfusionbackend.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PermissionName implements GrantedAuthority {

    READ,
    WRITE,
    UPDATE,
    DELETE,
    GRANT_ADMIN,
    UNRESTRICTED;

    @Override
    public String getAuthority() {
        return "SCOPE_" + name();
    }
}
