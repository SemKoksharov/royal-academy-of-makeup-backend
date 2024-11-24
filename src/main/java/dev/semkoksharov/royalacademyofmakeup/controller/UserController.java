package dev.semkoksharov.royalacademyofmakeup.controller;

import dev.semkoksharov.royalacademyofmakeup.dto.UserRequest;
import dev.semkoksharov.royalacademyofmakeup.dto.UserResponse;
import dev.semkoksharov.royalacademyofmakeup.dto.UserUpdate;
import dev.semkoksharov.royalacademyofmakeup.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long userId) {
        String delResult = userService.deleteUserById(userId);

        return new ResponseEntity<>(delResult, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdate request) {

    }

}
