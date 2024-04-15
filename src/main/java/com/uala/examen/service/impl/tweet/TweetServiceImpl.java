package com.uala.examen.service.impl.tweet;

import com.uala.examen.dto.tweet.request.CreateTweetRequestDTO;
import com.uala.examen.dto.tweet.TweetDTO;
import com.uala.examen.persistence.entity.tweet.Tweet;
import com.uala.examen.persistence.entity.user.User;
import com.uala.examen.repository.tweet.TweetRepository;
import com.uala.examen.service.impl.user.UserServiceImpl;
import com.uala.examen.service.tweet.TweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class TweetServiceImpl implements TweetService {

    private UserServiceImpl userService;
    private TweetRepository tweetRepository;

    @Override
    public Tweet createTweet(Long userId, CreateTweetRequestDTO requestDTO) {
        log.info("TweetServiceImpl.createTweet - userId {}, request {}", userId, requestDTO);

        userService.verifyUser(userId);

        User user = userService.getReferenceById(userId);

        Tweet tweet = new Tweet(requestDTO);

        user.addTweet(tweet);

        return tweetRepository.save(tweet);
    }

    @Override
    public Page<TweetDTO> getAllTweets(Pageable pageable) {
        return tweetRepository.getAllTweets(pageable);
    }

    @Override
    public Page<Tweet> getTweetsByUserId(Long userId, Pageable pageable) {
        userService.verifyUser(userId);

        return tweetRepository.getTweetsByFollowingUserId(userId, pageable);
    }

}
