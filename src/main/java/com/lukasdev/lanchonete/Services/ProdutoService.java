package com.lukasdev.lanchonete.Services;


import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.ProdutoRequest;
import com.lukasdev.lanchonete.Entities.Produto;
import com.lukasdev.lanchonete.Exceptions.ProdutoNaoEncontradoException;
import com.lukasdev.lanchonete.Repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//anotação Service
@Service
public class ProdutoService {
    // injeção de dependencia
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    //Metodo Cria produto , recebe um Produto Request como parametro
    public ProdutoResponse criarProduto(ProdutoRequest request) {

        //crio um Objeto do tipo Produto e inicializo
        Produto novoProduto = new Produto();
        // Seto o objeto com os atributos do request passado pelo usuario
        novoProduto.setNome(request.getNome());
        novoProduto.setValor(request.getValor());

        // salvo o novo produto usando o repository injetado
        repository.save(novoProduto);
        // crio um objeto do tipo ProdutoResponse e inicializo
        ProdutoResponse response = new ProdutoResponse();
        // seto os seus atributos usando os atributos que foram salvos no objeto do tipo Produto
        response.setNome(novoProduto.getNome());
        response.setValor(novoProduto.getValor());
        response.setId(novoProduto.getId());

        //retorna o objeto produto response ja que o metodo e do mesmo tipo
        return response;

    }

    //Metodo List do tipo ProdutoResponse que lista todos
    public List<ProdutoResponse> listarTodos() {
        //Crio uma lista de Produto com nome de produtos recebendo como valor repository find all
        List<Produto> produtos = repository.findAll();
        //Crio uma Lista de Produtos responses para adicionar os dtos
        List<ProdutoResponse> responses = new ArrayList<>();
        //faço um for criando uma variavel do tipo produto chamada produto ,que vai pecorrer todos os  produtos
        for (Produto produto : produtos) {
            //Crio uma variavel response do tipo Produto response e inicializo
            ProdutoResponse response = new ProdutoResponse();
            // em seguida seto esse response com os atributos do produto
            response.setValor(produto.getValor());
            response.setId(produto.getId());
            response.setNome(produto.getNome());
            //adiciono esse response na lista de responses
            responses.add(response);

        }
        //retorno essa lista de responses
        return responses;
    }

    public ProdutoResponse buscarPorId(Long id) {

        Produto produto = repository.findById(id).orElseThrow(() ->

                new ProdutoNaoEncontradoException(id));

        ProdutoResponse response = new ProdutoResponse();

        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setValor(produto.getValor());

        return response;
    }

    public ProdutoResponse deletarProduto(Long id) {

        Produto produto = repository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradoException(id));

        repository.delete(produto);

        ProdutoResponse response = new ProdutoResponse();

        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setValor(produto.getValor());

        return response;

    }

    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest request) {

        Produto produto = repository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradoException(id));

        produto.setValor(request.getValor());
        produto.setNome(request.getNome());

        repository.save(produto);

        ProdutoResponse response = new ProdutoResponse();

        response.setId(produto.getId());
        response.setValor(produto.getValor());
        response.setNome(produto.getNome());

        return response;

    }

}
