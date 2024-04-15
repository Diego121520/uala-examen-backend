package com.uala.examen.service.user;

import com.uala.examen.dto.user.request.CreateUserRequestDTO;
import com.uala.examen.persistence.entity.user.User;

import java.util.List;

public interface UserService {

    User createUser(CreateUserRequestDTO requestDTO);

    User getUserById(Long id);

    void addFollower(Long userId, Long followId);

    User getReferenceById(Long userId);

    List<User> getFollowersByUserId(Long userId);

    List<User> getFollowingByUserId(Long userId);


}
