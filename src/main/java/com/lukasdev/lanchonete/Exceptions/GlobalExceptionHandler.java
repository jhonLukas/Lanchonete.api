package com.lukasdev.lanchonete.Exceptions;

import com.lukasdev.lanchonete.Dto.ResponseDto.ErrorResponse;
import com.lukasdev.lanchonete.Entities.Pedido;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> trataException(Exception ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> trataProdutoException(ProdutoNaoEncontradoException ex) {

        ErrorResponse response = new ErrorResponse();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> trataPedidoException(PedidoNaoEncontradoException ex) {

        ErrorResponse response = new ErrorResponse();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMessage(ex.getMessage());
        response.setErro(HttpStatus.NOT_FOUND.getReasonPhrase());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ItemPedidoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> trataItemPedidoException(ItemPedidoNaoEncontradoException ex) {

        ErrorResponse response = new ErrorResponse();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }


}
