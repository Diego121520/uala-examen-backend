package com.uala.examen.message;

public enum SuccessMessage {
    SUCCESSFULLY_FOLLOWING("Usuario seguido exitosamente"),
    SUCCESSFULLY_TWEET("Tweet creado exitosamente")
    ;


    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
