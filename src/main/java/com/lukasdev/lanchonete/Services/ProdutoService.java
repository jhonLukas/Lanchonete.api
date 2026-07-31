package com.lukasdev.lanchonete.Services;


import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.ProdutoRequest;
import com.lukasdev.lanchonete.Entities.Produto;
import com.lukasdev.lanchonete.Exceptions.ProdutoNaoEncontradoException;
import com.lukasdev.lanchonete.Mapper.ProdutoMapper;
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

        //Uso a classe ProdutoMapper para transformar o request em um objeto
        Produto novoProduto = ProdutoMapper.toEntity(request);

        // salvo o novo produto usando o repository injetado
        repository.save(novoProduto);
        //Uso a classe ProdutoMapper para transformar a entidade em um response
        return ProdutoMapper.toResponse(novoProduto);

    }

    //Metodo List do tipo ProdutoResponse que lista todos
    public List<ProdutoResponse> listarTodos() {
        //Crio uma lista de Produto com nome de produtos recebendo como valor repository find all
        List<Produto> produtos = repository.findAll();
        //Crio uma Lista de Produtos responses para adicionar os dtos
        List<ProdutoResponse> responses = new ArrayList<>();
        //faço um for criando uma variavel do tipo produto chamada produto ,que vai pecorrer todos os  produtos
        for (Produto produto : produtos) {

            responses.add(  ProdutoMapper.toResponse(produto));
        }
        //retorno essa lista de responses
        return responses;
    }

    public ProdutoResponse buscarPorId(Long id) {

        Produto produto = repository.findById(id).orElseThrow(() ->

                new ProdutoNaoEncontradoException(id));

        return ProdutoMapper.toResponse(produto);

    }

    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest request) {

        Produto produto = repository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradoException(id));

        produto.setValor(request.getValor());
        produto.setNome(request.getNome());

        repository.save(produto);

        return ProdutoMapper.toResponse(produto);

    }

    public void deletarProduto(Long id) {

        Produto produto = repository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradoException(id));

        repository.delete(produto);

    }

}
