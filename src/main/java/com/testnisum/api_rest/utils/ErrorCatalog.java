package com.testnisum.api_rest.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCatalog {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"Usuario no encontrado."),

    INVALID_EMAIL(HttpStatus.BAD_REQUEST.value(),"Correo inválido"),

    EXISTS_USER(HttpStatus.BAD_REQUEST.value(),"El usuario ya existe."),

    USER_DELETED(HttpStatus.OK.value(),"El usuario se eliminado correctamente."),

    INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(),"Contraseña incorrecta."),

    GENERIC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Ha ocurrido un error inesperado.");


    private final String code;

    private final String message;


    ErrorCatalog(int code, String message){
        this.code = String.valueOf(code);
        this.message = message;
    }
}
