package com.lukasdev.lanchonete.Controller;

import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.PedidoRequest;
import com.lukasdev.lanchonete.Dto.ResquestDto.ProdutoRequest;
import com.lukasdev.lanchonete.Services.ProdutoService;
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


    @PostMapping
    public ResponseEntity<ProdutoResponse> create(@Valid @RequestBody ProdutoRequest request) {

        ProdutoResponse response = service.criarProduto(request);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> findAll() {

        return ResponseEntity.ok(service.listarTodos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable("id") Long requestId) {

        return ResponseEntity.ok(service.buscarPorId(requestId));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long requestId) {

        service.deletarProduto(requestId);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> updateById(@PathVariable("id") Long requestId,
                                                      @Valid @RequestBody ProdutoRequest request) {

        return ResponseEntity.ok(service.atualizarProduto(requestId, request));


    }

}
