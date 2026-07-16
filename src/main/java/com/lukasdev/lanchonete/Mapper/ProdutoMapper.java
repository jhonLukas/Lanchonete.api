package com.lukasdev.lanchonete.Mapper;

import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.ProdutoRequest;
import com.lukasdev.lanchonete.Entities.Produto;

public class ProdutoMapper {


    public static ProdutoResponse toResponse (Produto produto){

        ProdutoResponse response = new ProdutoResponse();

        response.setId(produto.getId());
        response.setValor(produto.getValor());
        response.setNome(produto.getNome());

        return response;

    }

    public static Produto toEntity (ProdutoRequest request){

        Produto produto = new Produto();

        produto.setNome(request.getNome());
        produto.setValor(request.getValor());

        return  produto;
    }
}
