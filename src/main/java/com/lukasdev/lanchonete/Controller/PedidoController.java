package com.lukasdev.lanchonete.Controller;

import com.lukasdev.lanchonete.Dto.ResponseDto.PedidoResponse;
import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.PedidoRequest;
import com.lukasdev.lanchonete.Entities.Pedido;
import com.lukasdev.lanchonete.Services.PedidoService;
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

    @PostMapping
    public ResponseEntity<PedidoResponse> create(@Valid @RequestBody PedidoRequest request) {

        PedidoResponse response = service.criaPedido(request);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> findAll() {

        return ResponseEntity.ok(service.listarPedidos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable("id") Long requestId) {

        return ResponseEntity.ok(service.listarPorId(requestId));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> updateById(@PathVariable("id") Long requestId,
                                                     @Valid @RequestBody PedidoRequest request) {

        return ResponseEntity.ok(service.atualizarPedido(requestId, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long requestId) {

        service.deletarPedido(requestId);

        return ResponseEntity.noContent().build();

    }

}
