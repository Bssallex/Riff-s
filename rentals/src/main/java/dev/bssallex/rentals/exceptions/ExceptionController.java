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

    //USER EXCEPTIONS
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

    //INSTRUMENT EXCEPTIONS
    @ExceptionHandler(ValueEnumInvalid.class)
    public ResponseEntity<Map<String, String>> handleValueEnumInvalid(ValueEnumInvalid exception){
        Map<String, String> ex = new HashMap<>();
        ex.put("Erro: ", exception.getMessage());
        ex.put("Mensagem: ", "Verifique se os valores informados são validos.");
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InstrumentNotFound.class)
    public ResponseEntity<Map<String, String>> handleInstrumentNotFound(InstrumentNotFound exception){
        Map<String, String> ex = new HashMap<>();
        ex.put("Erro: ", exception.getMessage());
        ex.put("Mensagem: ", "Verifique se o instrumento existe no sistema.");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    // RENTALS EXCEPTIONS

    @ExceptionHandler(RentalsNotFound.class)
    public ResponseEntity<Map<String, String>> handleHentalsNotFound(RentalsNotFound exception){
        Map<String, String> ex = new HashMap<>();
        ex.put("Erro: ", exception.getMessage());
        ex.put("Mensagem: ", "Verifique se este aluguel existe no sistema.");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundUserRentals.class)
    public ResponseEntity<Map<String, String>> handleHentalsNotFound(NotFoundUserRentals exception){
        Map<String, String> ex = new HashMap<>();
        ex.put("Erro: ", exception.getMessage());
        ex.put("Mensagem: ", "Verifique se este usuário tem algum registro de aluguel no sistema.");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}
