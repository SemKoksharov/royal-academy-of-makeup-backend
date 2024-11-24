package dev.semkoksharov.royalacademyofmakeup.service;

import dev.semkoksharov.royalacademyofmakeup.dto.ChangePasswordRequest;
import dev.semkoksharov.royalacademyofmakeup.dto.UserRequest;
import dev.semkoksharov.royalacademyofmakeup.dto.UserResponse;
import dev.semkoksharov.royalacademyofmakeup.dto.UserUpdate;
import dev.semkoksharov.royalacademyofmakeup.model.Role;
import dev.semkoksharov.royalacademyofmakeup.model.UserEntity;
import dev.semkoksharov.royalacademyofmakeup.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserResponse registerUser(UserRequest userRequest) {
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        if (userEntity.getRole() == null) {
            userEntity.setRole(Role.STUDENT);
        }

        UserEntity savedUser = userRepository.saveAndFlush(userEntity);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserResponse.class)).toList();
    }

    public String deleteUserById(Long id) {
        if (id < 0) throw new IllegalArgumentException("[Delete Error] ID cannot be negative.");

        if (!this.existsById(id)) throw new EntityNotFoundException(
                String.format("[Delete error] User with id %d not found", id)
        );

        userRepository.deleteById(id);

        return String.format("[Delete success] User with id %d deleted", id);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public UserResponse updateUser(UserUpdate update) {

        UserEntity userToUpdate = userRepository
                .findById(update.userToUpdateId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("[Update error] User with id %d not found", update.userToUpdateId()
                        )));

        modelMapper.map(update, userToUpdate);
        userRepository.saveAndFlush(userToUpdate);

        return modelMapper.map(userToUpdate, UserResponse.class);
    }


    public String changePassword(ChangePasswordRequest request) {

        var user = userRepository
                .findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(
                                "[Change password error] User with id %d not found", request.userId()
                        )));

        if (passwordEncoder.matches(request.oldPassWord(), user.getPassword())) {

            String encodedNewPassword = passwordEncoder.encode(request.oldPassWord());
            user.setPassword(encodedNewPassword);

            return String.format("[Change password success] User with id %d changed password", request.userId());
        } else {

            return "[Change password error] Passwords do not match, please try again";
        }
    }


}

