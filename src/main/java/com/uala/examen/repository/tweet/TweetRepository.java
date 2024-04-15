package com.uala.examen.repository.tweet;

import com.uala.examen.dto.tweet.TweetDTO;
import com.uala.examen.persistence.entity.tweet.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT t from Tweet t")
    Page<TweetDTO> getAllTweets(Pageable pageable);
    @Query("SELECT t FROM Tweet t WHERE t.user IN (SELECT u.following from User u WHERE u.id = :userId)")
    Page<Tweet> getTweetsByFollowingUserId(@Param("userId") Long userId, Pageable pageable);
}
