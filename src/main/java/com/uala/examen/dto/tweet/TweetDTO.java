package com.uala.examen.dto.tweet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uala.examen.persistence.entity.tweet.Tweet;

import java.time.LocalDateTime;

public record TweetDTO (

        Long id,
        String username,
        String message,
        @JsonProperty("created_at")
        LocalDateTime createdAt
) {
        public TweetDTO (Tweet tweet) {
                this(tweet.getId(), tweet.getUser().getUsername(), tweet.getMessage(), tweet.getCreatedAt());
        }

}
