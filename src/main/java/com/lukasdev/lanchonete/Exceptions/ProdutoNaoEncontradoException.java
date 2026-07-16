package com.lukasdev.lanchonete.Exceptions;

import com.lukasdev.lanchonete.Entities.Produto;

public class ProdutoNaoEncontradoException extends RuntimeException{

public ProdutoNaoEncontradoException(Long id) {

    super("Produto com o id " + id + " não foi encontrado!");

}

}
