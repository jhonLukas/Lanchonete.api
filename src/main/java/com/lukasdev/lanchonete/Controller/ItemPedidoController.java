package com.lukasdev.lanchonete.Controller;


import com.lukasdev.lanchonete.Dto.ResponseDto.ItemPedidoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.ItemPedidoRequest;
import com.lukasdev.lanchonete.Entities.ItemPedido;
import com.lukasdev.lanchonete.Services.ItemPedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {

    private final ItemPedidoService service;

    public ItemPedidoController(ItemPedidoService service) {

        this.service = service;

    }

    @Operation(
            summary = "Criar item do pedido",
            description = "Cria um novo item em um pedido"
    )
    @PostMapping
    public ResponseEntity<ItemPedidoResponse> create(@Valid @RequestBody ItemPedidoRequest request) {

        ItemPedidoResponse response = service.adicionarItem(request);

        return ResponseEntity.ok(response);

    }

    @Operation(
            summary = "Listar itens do pedido",
            description = "Listar todos os itens dos pedidos"
    )
    @GetMapping
    public ResponseEntity<List<ItemPedidoResponse>> findAll() {

        return ResponseEntity.ok(service.listarTodos());

    }

    @Operation(
            summary = "Buscar por ID",
            description = "Retorna um item do pedido a partir do seu identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> findById(

            @Parameter(description = "Identificador Unico do ItemPedido")
            @PathVariable("id") Long itemPedidoId) {

        return ResponseEntity.ok(service.listarPorId(itemPedidoId));

    }

    @Operation(
            summary = "Atualiza item do pedido",
            description = "Atualiza item do pedido pelo seu ID"

    )
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> updateById(

            @Parameter(description = "Identificador unico do item Pedido")
            @PathVariable("id") Long itemPedidoId,
            @Valid @RequestBody ItemPedidoRequest request) {

        return ResponseEntity.ok(service.atualizarPorId(itemPedidoId, request.getQuantidade()));

    }

    @Operation(
            summary = "Excluir ItemPedido",
            description = "Metodo usado para excluir ItemPedido atraves de seu ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Indentificador Unico do ItemPedido")
            @PathVariable("id") Long itemPedidoId) {

        service.deletarItempedido(itemPedidoId);

        return ResponseEntity.noContent().build();

    }


}
