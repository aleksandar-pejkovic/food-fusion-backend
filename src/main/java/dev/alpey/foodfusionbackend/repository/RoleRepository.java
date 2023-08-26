package dev.alpey.foodfusionbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.enums.RoleName;
import dev.alpey.foodfusionbackend.model.entity.Role;

@Repository
public interface RoleRepository extends ListCrudRepository<Role, Long> {

    @Query(
            "SELECT CASE WHEN EXISTS ( "
                    + "SELECT u "
                    + "FROM User u "
                    + "JOIN u.roles r "
                    + "WHERE u.username = :username "
                    + "AND r.name = 'ADMIN' "
                    + ") THEN true ELSE false END "
    )
    boolean hasAdminRole(@Param("username") String username);

    Role findByName(RoleName name);
}
