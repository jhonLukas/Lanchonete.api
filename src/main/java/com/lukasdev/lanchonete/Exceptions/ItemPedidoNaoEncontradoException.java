package com.lukasdev.lanchonete.Exceptions;

import com.lukasdev.lanchonete.Dto.ResponseDto.ItemPedidoResponse;

public class ItemPedidoNaoEncontradoException extends RuntimeException{

    public ItemPedidoNaoEncontradoException(Long id){

        super("ItemPedido com o id " + id + " não foi encontrado!");
    }
}
