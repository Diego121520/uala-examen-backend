package com.uala.examen.controller.tweet;

import com.uala.examen.dto.tweet.request.CreateTweetRequestDTO;
import com.uala.examen.dto.tweet.TweetDTO;
import com.uala.examen.exception.generic.GenericException;
import com.uala.examen.message.ErrorMessage;
import com.uala.examen.persistence.entity.tweet.Tweet;
import com.uala.examen.service.impl.tweet.TweetServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/tweet")
public class TweetController {

    private TweetServiceImpl tweetService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Tweet> createTweet(@PathVariable Long userId, @Valid @RequestBody CreateTweetRequestDTO requestDTO) {
        log.info("TweetController.createTweet - {} ", requestDTO);

        return ResponseEntity.ok().body(tweetService.createTweet(userId, requestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TweetDTO>> getAllTweets(@RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageable = buildPageable(page, size);

        return ResponseEntity.ok().body(tweetService.getAllTweets(pageable));
    }

    @GetMapping("timeline/user/{userId}")
    public ResponseEntity<Page<Tweet>> getTimelineByUserId(@PathVariable Long userId,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size) {

        log.info("TweetController.getTimelineByUserId - {} ", userId);

        Pageable pageable = buildPageable(page, size);

        return ResponseEntity.ok().body(tweetService.getTweetsByUserId(userId, pageable));
    }

    private Pageable buildPageable(Integer page, Integer size) {
        Pageable pageable;

        if(page < 0 ){
            throw new GenericException(ErrorMessage.INVALID_VALUE.getMessage("page"), HttpStatus.BAD_REQUEST);
        }

        if(size <= 0){
            throw new GenericException(ErrorMessage.INVALID_VALUE.getMessage("size"), HttpStatus.BAD_REQUEST);
        }

        pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return pageable;
    }

}
