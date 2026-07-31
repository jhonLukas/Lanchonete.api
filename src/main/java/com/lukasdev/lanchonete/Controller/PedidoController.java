package com.lukasdev.lanchonete.Controller;

import com.lukasdev.lanchonete.Dto.ResponseDto.PedidoResponse;
import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.PedidoRequest;
import com.lukasdev.lanchonete.Entities.Pedido;
import com.lukasdev.lanchonete.Services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cria pedido",
            description = "Metodo utilizado para criar um pedido")
    @PostMapping
    public ResponseEntity<PedidoResponse> create(@Valid @RequestBody PedidoRequest request) {

        PedidoResponse response = service.criaPedido(request);

        return ResponseEntity.ok(response);

    }

    @Operation(
            summary = "Listar todos",
            description = "Esse metodo lista todos os pedidos feitos"
    )
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> findAll() {

        return ResponseEntity.ok(service.listarPedidos());

    }

    @Operation(
            summary = "Buscar por ID",
            description = "Faz uma busca do pedido pelo ID do pedido"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(

            @Parameter(description = "Identificador unico do pedido")
            @PathVariable("id") Long requestId) {

        return ResponseEntity.ok(service.listarPorId(requestId));

    }

    @Operation(
            summary = "Atualiza pedido",
            description = "Atualiza o status do pedido"
    )
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> updateById(

            @Parameter(description = "Identificador unico do pedido")
            @PathVariable("id") Long requestId,
            @Valid @RequestBody PedidoRequest request) {

        return ResponseEntity.ok(service.atualizarPedido(requestId, request));

    }

    @Operation(
            summary = "Excluir pedido",
            description = "Excluir pedido usando o seu ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long requestId) {

        service.deletarPedido(requestId);

        return ResponseEntity.noContent().build();

    }

}
