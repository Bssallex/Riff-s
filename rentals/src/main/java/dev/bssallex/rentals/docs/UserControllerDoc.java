package dev.bssallex.rentals.docs;

import dev.bssallex.rentals.dtos.request.UserRequest;
import dev.bssallex.rentals.dtos.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Usuários", description = "Recurso responsável pelo gerenciamento de usuários.")
public interface UserControllerDoc {

    @Operation(summary = "Listar todos os usuários", description = "Retorna todos os usuários que estão cadastrados no sistema")
    @ApiResponse(
            responseCode = "200",
            description = "Lista de usuários",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))
    )
    public ResponseEntity<List<UserResponse>> listAllUsers();


    @Operation(summary = "Buscar usuário por ID", description = "Busca um usuário cadastrado com base no seu id")
    @ApiResponse(
            responseCode = "200",
            description = "Usuário",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content()
    )
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id);

    @Operation(summary = "Cadastrar um novo usuário", description = "Cria o cadastro de um novo usuário no sistema")
    @ApiResponse(
            responseCode = "201",
            description = "Usuário criado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request);


    @Operation(summary = "Alterar dados do usuário", description = "Busca um usuário pelo id e altera seus dados")
    @ApiResponse(
            responseCode = "200",
            description = "Usuário",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content()
    )
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest request);


    @Operation(summary = "Deletar usuário", description = "Busca um usuário pelo id e deleta seu cadastro")
    @ApiResponse(
            responseCode = "204",
            description = "Usuário deletado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content()
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long id);

}
