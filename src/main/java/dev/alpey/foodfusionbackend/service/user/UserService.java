package dev.alpey.foodfusionbackend.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.UserDTO;
import dev.alpey.foodfusionbackend.model.entity.User;
import dev.alpey.foodfusionbackend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = mapper.convertToEntity(userDTO);
        User savedUser = repository.save(user);
        return mapper.convertToDto(savedUser);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = mapper.convertToExistingEntity(userDTO);
        User updatedUser = repository.save(existingUser);
        return mapper.convertToDto(updatedUser);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public UserDTO loadCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = repository.findByUsername(username).orElseThrow();
        return mapper.convertToDto(currentUser);
    }

    public List<UserDTO> loadAllUsers() {
        return mapper.convertToDtoList(repository.findAll());
    }
}
