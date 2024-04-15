package com.uala.examen.controller.user;

import com.uala.examen.dto.user.request.CreateUserRequestDTO;
import com.uala.examen.dto.generic.GenericResponseDTO;
import com.uala.examen.message.SuccessMessage;
import com.uala.examen.persistence.entity.user.User;
import com.uala.examen.service.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequestDTO requestDTO) {
        log.info("userController.createUser - {} ", requestDTO);

        return ResponseEntity.ok(service.createUser(requestDTO));
    }

    @PostMapping("/{userId}/follow/{followId}")
    public ResponseEntity<GenericResponseDTO> followUser(@PathVariable @NotNull Long userId, @PathVariable @NotNull Long followId) {
        log.info("userController.followUser - userId {}, followId {} ", userId, followId);

       service.addFollower(userId, followId);

        return ResponseEntity.ok().body(new GenericResponseDTO(SuccessMessage.SUCCESSFULLY_FOLLOWING.getMessage()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        log.info("userController.getUser - userId {} ", userId);

        return ResponseEntity.ok().body(service.getUserById(userId));
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<User>> getFollowersByUserId(@PathVariable Long userId) {
        log.info("userController.getFollowersByUserId - userId {} ", userId);

        return ResponseEntity.ok().body(service.getFollowersByUserId(userId));
    }

    @GetMapping("{userId}/following")
    public ResponseEntity<List<User>> getFollowingByUserId(@PathVariable Long userId) {
        log.info("userController.getFollowingByUserId - userId {} ", userId);

        return ResponseEntity.ok().body(service.getFollowingByUserId(userId));
    }
}
