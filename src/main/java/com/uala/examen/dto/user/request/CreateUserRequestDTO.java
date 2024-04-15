package com.uala.examen.dto.user.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequestDTO (
       @NotEmpty String username
        ) {


}
