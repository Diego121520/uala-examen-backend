package com.uala.examen.dto.tweet.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateTweetRequestDTO (
       @NotEmpty String message
) {

}
