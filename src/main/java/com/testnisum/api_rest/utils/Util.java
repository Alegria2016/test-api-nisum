package com.testnisum.api_rest.utils;

import com.testnisum.api_rest.models.dtos.RegisterResponse;
import com.testnisum.api_rest.models.dtos.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("CheckEmailCorrectly")
public class Util {
    public boolean isPasswordValid(String password) {
        return password.matches(Constant.REGEX_PASSWORD);
    }



    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(Constant.REGEX_EMAIL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public void validateDataUser(UserRequest request){
        RegisterResponse response = new RegisterResponse();

        if(!isEmailValid(request.getEmail())){
            response.setMessage("El correo no es valido, debe contener el siguiente formato: ejemplo@.com");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setValid(Boolean.FALSE);
            return;
        }

        if(!isPasswordValid(request.getPassword())){
            response.setMessage("La contraseña no es valida, debe contener al menos 8 caractres con Lestras minusculas, mayúsculas, numeros y un caracter especial ()");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setValid(Boolean.FALSE);
        }


    }

}
