package dev.bssallex.rentals.docs;

import dev.bssallex.rentals.dtos.request.InstrumentRequest;
import dev.bssallex.rentals.dtos.response.InstrumentResponse;
import dev.bssallex.rentals.dtos.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Instrumentos", description = "Recurso responsável pelo gerenciamento de instrumentos.")
public interface InstrumentControllerDoc {

    @Operation(summary = "Listar todos os instrumentos", description = "Retorna todos os instrumentos cadastrados no sistema")
    @ApiResponse(
            responseCode = "200",
            description = "Lista de instrumentos",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))
    )
    public ResponseEntity<List<InstrumentResponse>> listAllInstruments();


    @Operation(summary = "Buscar instrumento por ID", description = "Busca um instrumento cadastrado com base no seu id")
    @ApiResponse(
            responseCode = "200",
            description = "Instrumento",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Instrumento não encontrado",
            content = @Content()
    )
    public ResponseEntity<InstrumentResponse> getInstrumentById(@PathVariable Long id);


    @Operation(summary = "Cadastrar um novo instrumento", description = "Cria o cadastro de um novo instrumento no sistema")
    @ApiResponse(
            responseCode = "201",
            description = "Instrumento criado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    public ResponseEntity<InstrumentResponse> createInstrument(@RequestBody InstrumentRequest request);


    @Operation(summary = "Alterar dados do instrumento", description = "Busca um instrumento pelo id e altera seus dados")
    @ApiResponse(
            responseCode = "200",
            description = "Instrumento",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Instrumento não encontrado",
            content = @Content()
    )
    public ResponseEntity<InstrumentResponse> updateInstrument(@PathVariable Long id, @RequestBody InstrumentRequest request);

    @Operation(summary = "Deletar instrumento", description = "Busca um instrumento pelo id e deleta seu cadastro")
    @ApiResponse(
            responseCode = "204",
            description = "Instrumento deletado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Instrumento não encontrado",
            content = @Content()
    )
    public ResponseEntity<Void> deleteInstrument(@PathVariable Long id);

}
