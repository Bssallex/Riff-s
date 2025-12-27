package dev.bssallex.rentals.docs;

import dev.bssallex.rentals.dtos.request.RentalsRequest;
import dev.bssallex.rentals.dtos.response.RentalsResponse;
import dev.bssallex.rentals.dtos.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Aluguéis", description = "Recurso responsável pelo gerenciamento de aluguéis.")
public interface RentalsControllerDoc {

    @Operation(summary = "Listar todos os aluguéis", description = "Retorna todos os aluguéis que estão agendados no sistema")
    @ApiResponse(
            responseCode = "200",
            description = "Lista de aluguéis",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))
    )
    @GetMapping("/all")
    public ResponseEntity<List<RentalsResponse>> findAllRentals();

    @Operation(summary = "Buscar aluguel por nome de usuário", description = "Retorna todos os aluguéis de um usuário cadastrado no sistema")
    @ApiResponse(
            responseCode = "200",
            description = "Aluguéis",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content()
    )
    @GetMapping("/user/{name}")
    public ResponseEntity<List<RentalsResponse>> findRentalsByNameUser(@PathVariable String name);

    @Operation(summary = "Registrar um novo aluguel", description = "Registra um novo aluguel no sistema")
    @ApiResponse(
            responseCode = "201",
            description = " Aluguel registrado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @PostMapping("/create")
    public ResponseEntity<RentalsResponse> createRentals(@RequestBody RentalsRequest request);

    @Operation(summary = "Deletar aluguel", description = "Busca um aluguel pela sua tag de criação e deleta do sistema")
    @ApiResponse(
            responseCode = "204",
            description = "Aluguel deletado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Aluguel não encontrado",
            content = @Content()
    )
    @DeleteMapping("/delete/{tag}")
    public ResponseEntity<Void> deleteRentals(@PathVariable String tag);

}
