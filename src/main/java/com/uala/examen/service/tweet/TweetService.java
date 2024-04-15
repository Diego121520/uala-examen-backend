package com.uala.examen.service.tweet;

import com.uala.examen.dto.tweet.TweetDTO;
import com.uala.examen.dto.tweet.request.CreateTweetRequestDTO;
import com.uala.examen.persistence.entity.tweet.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TweetService {

    Tweet createTweet(Long userId, CreateTweetRequestDTO requestDTO);

    Page<TweetDTO> getAllTweets(Pageable pageable);

    Page<Tweet> getTweetsByUserId(Long userId, Pageable pageable);
}
