package dev.bssallex.email.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(SendEmailFailed.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(SendEmailFailed exception){
        Map<String, String> ex = new HashMap<>();
        ex.put("Erro: ", exception.getMessage());
        ex.put("Mensagem: ", "Verique as configurações no serviço de email.");
        return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
    }
}
