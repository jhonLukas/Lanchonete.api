package com.lukasdev.lanchonete.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoResponse {

    private Long id;

    private Long pedidoId;

    private int quantidade;

    private String produtoNome;

    private BigDecimal subtotal;
}
