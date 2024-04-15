package com.uala.examen.dto.user;

import com.uala.examen.persistence.entity.user.User;

public record UserDTO (
        Long id,
        String username
        )
{
        public UserDTO (User user) {
                this(user.getId(), user.getUsername());
        }
}
