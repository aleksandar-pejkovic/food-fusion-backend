package dev.alpey.foodfusionbackend.configuration.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dev.alpey.foodfusionbackend.model.entity.Permission;
import dev.alpey.foodfusionbackend.model.entity.Role;
import dev.alpey.foodfusionbackend.model.entity.User;
import dev.alpey.foodfusionbackend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username '" + username + "' not found!"));
        return buildUserDetails(user);
    }

    private org.springframework.security.core.userdetails.User buildUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<String> permissions = getPermissions(roles);
        return getGrantedAuthorities(permissions);
    }

    private List<String> getPermissions(Collection<Role> roles) {
        List<String> grantedAuthorities = new ArrayList<>();
        Set<Permission> permissions = new HashSet<>();
        for (Role role : roles) {
            grantedAuthorities.add(role.getName().getAuthority());
            permissions.addAll(role.getPermissions());
        }
        for (Permission permission : permissions) {
            grantedAuthorities.add(permission.getName().name());
        }
        return grantedAuthorities;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }
}
