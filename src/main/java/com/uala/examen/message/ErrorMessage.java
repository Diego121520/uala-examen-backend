package com.uala.examen.message;

public enum ErrorMessage {
    //USER
    USER_NOT_FOUND("Usuario '%s' no encontrado"),
    USER_ALREADY_EXISTS("El usuario ya existe"),
    INVALID_USER_ID("ID de usuario inválido"),
    USER_ALREADY_FOLLOWED("Ya se encuentra siguiendo al usuario"),
    SELF_FOLLOW_ERROR("No puedes seguirte a ti mismo"),

    //TWEET
    TWEET_NOT_FOUND("Tweet no encontrado"),
    MESSAGE_TOO_LONG("El mensaje no debe superar los 280 caracteres"),

    //GENERIC
    EMPTY_MESSAGE("El mensaje no puede estar vacío"),
    ENDPOINT_NOT_FOUND("No se ha encontrado el endpoint solicitado"),
    INTERNAL_SERVER_ERROR("Error interno del servidor"),
   INVALID_VALUE("El atributo '%s' no puede ser menor a cero");

    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
