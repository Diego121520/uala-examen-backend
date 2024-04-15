package com.uala.examen.service.impl.user;

import com.uala.examen.dto.user.request.CreateUserRequestDTO;
import com.uala.examen.exception.generic.GenericException;
import com.uala.examen.message.ErrorMessage;
import com.uala.examen.persistence.entity.user.User;
import com.uala.examen.repository.user.UserRepository;
import com.uala.examen.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public User createUser(CreateUserRequestDTO requestDTO) {

        if(repository.existsByUsername(requestDTO.username())) {
            throw new GenericException(ErrorMessage.USER_ALREADY_EXISTS.getMessage(), HttpStatus.BAD_REQUEST);
        }

        User user = new User(requestDTO);

        return repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GenericException(ErrorMessage.USER_NOT_FOUND.getMessage(id), HttpStatus.NOT_FOUND));
    }

    @Override
    public void addFollower(Long userId, Long followingId) {
        verifyUser(userId);
        verifyUser(followingId);

        if(userId.equals(followingId)) {
            throw new GenericException(ErrorMessage.SELF_FOLLOW_ERROR.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if(repository.isUserAlreadyFollowing(userId, followingId)) {
            throw new GenericException(ErrorMessage.USER_ALREADY_FOLLOWED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        User user = repository.getReferenceById(userId);

        User following = repository.getReferenceById(followingId);

        user.addFollowing(following);

        repository.save(user);
    }

    public void verifyUser(Long userId) {
        if(!repository.existsById(userId)) {
            throw new GenericException(ErrorMessage.USER_NOT_FOUND.getMessage(userId), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User getReferenceById(Long userId) {
        return repository.getReferenceById(userId);
    }

    @Override
    public List<User> getFollowersByUserId(Long userId) {
        verifyUser(userId);

        return repository.getFollowersByUserId(userId);
    }

    @Override
    public List<User> getFollowingByUserId(Long userId) {
        verifyUser(userId);

        return repository.getFollowingByUserId(userId);
    }


}
