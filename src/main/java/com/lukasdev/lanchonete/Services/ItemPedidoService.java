package com.lukasdev.lanchonete.Services;

import com.lukasdev.lanchonete.Dto.ResponseDto.ItemPedidoResponse;
import com.lukasdev.lanchonete.Dto.ResponseDto.PedidoResponse;
import com.lukasdev.lanchonete.Dto.ResponseDto.ProdutoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.ItemPedidoRequest;
import com.lukasdev.lanchonete.Entities.ItemPedido;
import com.lukasdev.lanchonete.Entities.Pedido;
import com.lukasdev.lanchonete.Entities.Produto;
import com.lukasdev.lanchonete.Enums.statusEnum;
import com.lukasdev.lanchonete.Exceptions.ItemPedidoNaoEncontradoException;
import com.lukasdev.lanchonete.Exceptions.PedidoNaoEncontradoException;
import com.lukasdev.lanchonete.Exceptions.ProdutoNaoEncontradoException;
import com.lukasdev.lanchonete.Mapper.ItemPedidoMapper;
import com.lukasdev.lanchonete.Repositories.ItemPedidoRepository;
import com.lukasdev.lanchonete.Repositories.PedidoRepository;
import com.lukasdev.lanchonete.Repositories.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPedidoService {
    //Injeto os repositories de Produto , pedido e item pedido

    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    //Crio os contrutores de ambos
    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }


    //crio o metodo do tipo Itempedidoresponse  chamado aidcionar item pedindo um itempedidoreques como parametro
    public ItemPedidoResponse adicionarItem(ItemPedidoRequest request) {
        //inicializo os Objetos do tipo produto e pedido e busco nos repositories de ambos se existem por id
        Produto produto = produtoRepository.findById(request.getProdutoId()).orElseThrow(() -> new ProdutoNaoEncontradoException(request.getPedidoId()));
        Pedido pedido = pedidoRepository.findById(request.getPedidoId()).orElseThrow(() -> new PedidoNaoEncontradoException(request.getPedidoId()));

        //validar status do pedido , se for entrega lançar mensagem
        if (pedido.getStatus() != statusEnum.ANALISE && pedido.getStatus() != statusEnum.PRODUCAO) {

            throw new RuntimeException("Pedido ja saiu para a entrega !!");
        }

        // Crio o Objeto do Tipo ItemPedido
        ItemPedido novoItemPedido = ItemPedidoMapper.toEntity(request, pedido, produto);

        // crio uma variavel do tipo Big decimal chamado subtotal , deve usar o metodo multiply pegando o atributo valor do objeto a cima e multiplicar pela quantidade do itempedido
        BigDecimal subtotal = produto.getValor().multiply(BigDecimal.valueOf(request.getQuantidade()));
        // Crio uma variavel do tipo Big decimal chamado novo total, deve pegar o atributo valor do pedido e usar o metodo add e somar o subtotal
        BigDecimal novoTotal = pedido.getTotal().add(subtotal);
        // em seguida setar o total do pedido pegando o novototal como valor
        pedido.setTotal(novoTotal);

        // salvo o item usando repository do itempedido
        itemPedidoRepository.save(novoItemPedido);
        // salvo o pedido usando o repository do pedido
        pedidoRepository.save(pedido);

        return ItemPedidoMapper.toResponse(novoItemPedido);
    }

    public List<ItemPedidoResponse> listarTodos() {

        List<ItemPedido> itemPedidolist = itemPedidoRepository.findAll();

        List<ItemPedidoResponse> responseList = new ArrayList<>();

        for (ItemPedido itemPedido : itemPedidolist) {

            responseList.add(ItemPedidoMapper.toResponse(itemPedido));
        }

        return responseList;

    }

    public ItemPedidoResponse listarPorId(Long id) {

        ItemPedido itemPedidobusca = itemPedidoRepository.findById(id).orElseThrow(() ->
                new ItemPedidoNaoEncontradoException(id));

        return ItemPedidoMapper.toResponse(itemPedidobusca);
    }

    public ItemPedidoResponse atualizarPorId(Long Id, int quantidade) {

        ItemPedido itemPedido = itemPedidoRepository.findById(Id).orElseThrow(() ->
                new ItemPedidoNaoEncontradoException(Id));

        itemPedido.setQuantidade(quantidade);

        BigDecimal novoSubtotal = itemPedido.getProduto().getValor().
                multiply(BigDecimal.valueOf(quantidade));

        itemPedido.setSubtotal(novoSubtotal);

        itemPedidoRepository.save(itemPedido);

        Pedido pedido = itemPedido.getPedido();

        List<ItemPedido> itens = pedido.getItens();

        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido iten : itens) {

            total = total.add(iten.getSubtotal());
        }

        pedido.setTotal(total);

        pedidoRepository.save(pedido);

       return ItemPedidoMapper.toResponse(itemPedido);

    }

    public void deletarItempedido(long id) {

        ItemPedido pedidoDelete = itemPedidoRepository.findById(id).orElseThrow(() ->
                new ItemPedidoNaoEncontradoException(id));

        itemPedidoRepository.delete(pedidoDelete);


    }

}