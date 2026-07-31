package com.lukasdev.lanchonete.Controller;

import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.PedidoRequest;
import com.lukasdev.lanchonete.Dto.ResquestDto.ProdutoRequest;
import com.lukasdev.lanchonete.Services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }


    @Operation(
            summary = "Criar produto",
            description = "Metodo utilizado para criar um novo produto "
    )
    @PostMapping
    public ResponseEntity<ProdutoResponse> create(@Valid @RequestBody ProdutoRequest request) {

        ProdutoResponse response = service.criarProduto(request);

        return ResponseEntity.ok(response);

    }


    @Operation(
            summary = "Listar todos os produtos",
            description = "Retorna uma lista de todos os produtos cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> findAll() {

        return ResponseEntity.ok(service.listarTodos());

    }

    @Operation(
            summary = "Obter produto pelo seu ID",
            description = "Retorna um produto desejado usando seu ID")

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findById(

            @Parameter(description = "Identificador único do produto")
            @PathVariable("id") Long requestId) {

        return ResponseEntity.ok(service.buscarPorId(requestId));

    }

    @Operation(
            summary = "Excluir produto",
            description = "Deleta um produto atraves de seu ID")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long requestId) {

        service.deletarProduto(requestId);

        return ResponseEntity.noContent().build();

    }

    @Operation(
            summary = "Atualiza Produto",
            description = "Atualiza um produto atraves de seu ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> updateById(

            @Parameter(description = "Identificador único do produto")
            @PathVariable("id") Long requestId,
            @Valid @RequestBody ProdutoRequest request) {

        return ResponseEntity.ok(service.atualizarProduto(requestId, request));


    }

}
