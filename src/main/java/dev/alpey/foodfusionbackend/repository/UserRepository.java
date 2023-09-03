package dev.alpey.foodfusionbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.User;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.roles r ON r.name = 'ADMIN'")
    List<User> findAdmins();

    @Query("SELECT u.email FROM User u JOIN u.roles r ON r.name = 'ADMIN'")
    List<String> findAdminEmails();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
