package dev.bssallex.rentals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFound exception){
        Map<String, String> ex = new HashMap<>();
        ex.put("Erro: ", exception.getMessage());
        ex.put("Mensagem: ", "Busque corretamente pelo nome ou verifique se o usuário existe no sistema.");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String, String> ex = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                        ex.put(error.getField(), error.getDefaultMessage()));

        ex.put("Mensagem: ", "Preencha os campos corretamente. 'name' não pode ser nulo e 'email' precisa ser neste formato: UsuarioExemplo@email.com");
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
