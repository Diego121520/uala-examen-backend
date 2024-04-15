package com.uala.examen.repository.user;

import com.uala.examen.persistence.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    @Query("SELECT u.followers FROM User u where u.id = :userId")
    List<User> getFollowersByUserId(@Param("userId") Long userId);

    @Query("SELECT u.following FROM User u where u.id = :userId")
    List<User> getFollowingByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM user_followers WHERE user_id = :userId AND following_id = :followingId", nativeQuery = true)
    Boolean isUserAlreadyFollowing(@Param("userId") Long userId, @Param("followingId") Long followingId);


}
