package com.uala.examen.persistence.entity.user;

import com.uala.examen.dto.user.request.CreateUserRequestDTO;
import com.uala.examen.persistence.entity.BaseEntity;
import com.uala.examen.persistence.entity.tweet.Tweet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<Tweet> tweets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private List<User> following = new ArrayList<>();

    public User(CreateUserRequestDTO requestDTO) {
        username = requestDTO.username();
    }

    public User() {}

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);

        tweet.setUser(this);
    }

    public void addFollowing(User user) {
        //agrego a los siguiendo de este usuario al nuevo usuario
        following.add(user);

        user.addFollower(this);
    }

    public void addFollower(User user) {
        followers.add(user);
    }

    public Integer getFollowers() {
        return followers.size();
    }

    public Integer getFollowing() {
        return following.size();
    }

    public Integer getTweets() {
        return tweets.size();
    }
}
