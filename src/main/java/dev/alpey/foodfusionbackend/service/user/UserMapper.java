package dev.alpey.foodfusionbackend.service.user;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.configuration.exceptions.user.EmailExistsException;
import dev.alpey.foodfusionbackend.configuration.exceptions.user.UsernameExistsException;
import dev.alpey.foodfusionbackend.enums.RoleName;
import dev.alpey.foodfusionbackend.model.dto.UserDTO;
import dev.alpey.foodfusionbackend.model.entity.Role;
import dev.alpey.foodfusionbackend.model.entity.User;
import dev.alpey.foodfusionbackend.repository.RoleRepository;
import dev.alpey.foodfusionbackend.repository.UserRepository;

@Component
public class UserMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    User convertToEntity(UserDTO userDTO) {
        validateUserDoesNotExist(userDTO.getUsername(), userDTO.getEmail());
        User user = mapper.map(userDTO, User.class);
        LocalDate currentDate = getCurrentDate();
        user.setCreatedDate(currentDate);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role userRole = roleRepository.findByName(RoleName.USER);
        user.setRoles(Collections.singleton(userRole));
        return user;
    }

    User convertToExistingEntity(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElseThrow();
        validateNewEmailDoesNotExist(userDTO.getEmail(), existingUser.getEmail());
        userDTO.setPassword(null);
        mapper.map(userDTO, existingUser);
        return existingUser;
    }

    UserDTO convertToDto(User user) {
        return mapper.map(user, UserDTO.class);
    }

    private static LocalDate getCurrentDate() {
        ZoneId belgradeTimeZone = ZoneId.of("Europe/Belgrade");
        LocalDate currentDate = LocalDate.now(belgradeTimeZone);
        return currentDate;
    }

    private void validateUserDoesNotExist(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameExistsException("Username already exists!");
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailExistsException("Email already exists!");
        }
    }

    private void validateNewEmailDoesNotExist(String providedEmail, String existingEmail) {
        if (emailIsChanged(providedEmail, existingEmail)
                && userRepository.existsByEmail(providedEmail)) {
            throw new EmailExistsException("Account with email '" + providedEmail + "' already exist");
        }
    }

    private static boolean emailIsChanged(String providedEmail, String existingEmail) {
        return !providedEmail.equals(existingEmail);
    }
}
