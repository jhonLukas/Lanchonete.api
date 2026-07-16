package com.lukasdev.lanchonete.Controller;


import com.lukasdev.lanchonete.Dto.ResponseDto.ItemPedidoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.ItemPedidoRequest;
import com.lukasdev.lanchonete.Entities.ItemPedido;
import com.lukasdev.lanchonete.Services.ItemPedidoService;
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


    @PostMapping
    public ResponseEntity<ItemPedidoResponse> create(@Valid @RequestBody ItemPedidoRequest request) {

        ItemPedidoResponse response = service.adicionarItem(request);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoResponse>> findAll() {

        return ResponseEntity.ok(service.listarTodos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> findById(@PathVariable("id") Long itemPedidoId) {

        return ResponseEntity.ok(service.listarPorId(itemPedidoId));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> updateById(@PathVariable("id") Long itemPedidoId, @Valid @RequestBody ItemPedidoRequest request) {

        return ResponseEntity.ok(service.atualizarPorId(itemPedidoId, request.getQuantidade()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long itemPedidoId) {

        service.deletarItempedido(itemPedidoId);

        return ResponseEntity.noContent().build();

    }


}
