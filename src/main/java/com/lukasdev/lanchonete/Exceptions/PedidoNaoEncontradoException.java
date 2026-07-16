package com.lukasdev.lanchonete.Exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {


    public PedidoNaoEncontradoException(Long id){

        super("Pedido com o id " + id + " não foi encontrado!");
    }
}
