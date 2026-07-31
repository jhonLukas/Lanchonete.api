package com.lukasdev.lanchonete.Services;

import com.lukasdev.lanchonete.Dto.ResponseDto.ItemPedidoResponse;
import com.lukasdev.lanchonete.Dto.ResponseDto.PedidoResponse;
import com.lukasdev.lanchonete.Dto.ResquestDto.PedidoRequest;
import com.lukasdev.lanchonete.Dto.ResquestDto.ProdutoRequest;
import com.lukasdev.lanchonete.Entities.Pedido;
import com.lukasdev.lanchonete.Exceptions.PedidoNaoEncontradoException;
import com.lukasdev.lanchonete.Mapper.PedidoMapper;
import com.lukasdev.lanchonete.Repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;


    public PedidoService(PedidoRepository pedidoRepository) {

        this.pedidoRepository = pedidoRepository;

    }

    public PedidoResponse criaPedido(PedidoRequest request) {

        Pedido pedido = PedidoMapper.toEntity(request);

        pedido.setTotal(BigDecimal.ZERO);
        pedido.setStatus(request.getStatus());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return PedidoMapper.toResponse(pedidoSalvo);

    }

    public List<PedidoResponse> listarPedidos() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        List<PedidoResponse> responses = new ArrayList<>();

        for (Pedido pedido : pedidos) {

            responses.add(PedidoMapper.toResponse(pedido));
        }

        return responses;
    }

    public PedidoResponse listarPorId(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() ->
                new PedidoNaoEncontradoException(id));

        return PedidoMapper.toResponse(pedido);

    }

    public PedidoResponse atualizarPedido(long id, PedidoRequest request) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() ->
                new PedidoNaoEncontradoException(id));

        pedido.setStatus(request.getStatus());

        pedidoRepository.save(pedido);

        return PedidoMapper.toResponse(pedido);

    }

    public void deletarPedido(long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() ->
                new PedidoNaoEncontradoException(id));

        pedidoRepository.delete(pedido);

    }


}
