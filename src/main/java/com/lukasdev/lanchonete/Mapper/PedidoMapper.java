package com.lukasdev.lanchonete.Mapper;

import com.lukasdev.lanchonete.Dto.ResponseDto.PedidoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.PedidoRequest;
import com.lukasdev.lanchonete.Entities.Pedido;

public class PedidoMapper {

    public static PedidoResponse toResponse(Pedido pedido){

        PedidoResponse response = new PedidoResponse();

        response.setId(pedido.getId());
        response.setStatus(pedido.getStatus());
        response.setTotal(pedido.getTotal());

        return response;

    }

    public static Pedido toEntity(PedidoRequest request){

        Pedido pedido = new Pedido();

        pedido.setTotal(request.getTotal());
        pedido.setStatus(request.getStatus());

        return pedido;

    }
}
