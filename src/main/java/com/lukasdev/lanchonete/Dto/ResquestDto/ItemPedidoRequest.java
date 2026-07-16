package com.lukasdev.lanchonete.Dto.ResquestDto;

import com.lukasdev.lanchonete.Entities.Pedido;
import com.lukasdev.lanchonete.Entities.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoRequest {

    @NotNull
    private Long produtoId;
    @NotNull
    private Long pedidoId;
    @Positive
    private int quantidade;


}
