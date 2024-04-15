package com.uala.examen.persistence.entity.tweet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uala.examen.dto.tweet.request.CreateTweetRequestDTO;
import com.uala.examen.persistence.entity.BaseEntity;
import com.uala.examen.persistence.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "tweets")
public class Tweet extends BaseEntity {

    @Size(min = 1, max = 280)
    @Column(nullable = false)
    private String message;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    public Tweet(CreateTweetRequestDTO requestDTO) {
        message = requestDTO.message();
        createdAt = LocalDateTime.now();
    }

    public Tweet() {}

}
