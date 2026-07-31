package com.lukasdev.lanchonete.Mapper;

import com.lukasdev.lanchonete.Dto.ResponseDto.ItemPedidoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.ItemPedidoRequest;
import com.lukasdev.lanchonete.Entities.ItemPedido;
import com.lukasdev.lanchonete.Entities.Pedido;
import com.lukasdev.lanchonete.Entities.Produto;

public class ItemPedidoMapper {


    public static ItemPedidoResponse toResponse(ItemPedido itemPedido) {

        ItemPedidoResponse itemPedidoResponse = new ItemPedidoResponse();

        itemPedidoResponse.setProdutoNome(itemPedido.getProduto().getNome());
        itemPedidoResponse.setQuantidade(itemPedido.getQuantidade());
        itemPedidoResponse.setId(itemPedido.getId());
        itemPedidoResponse.setPedidoId(itemPedido.getPedido().getId());
        itemPedidoResponse.setSubtotal(itemPedido.getSubtotal());

        return itemPedidoResponse;


    }

    public static ItemPedido toEntity(ItemPedidoRequest request , Pedido pedido ,  Produto produto) {

        ItemPedido itemPedido = new ItemPedido();


        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(request.getQuantidade());

        return itemPedido;


    }
}
