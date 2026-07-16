package com.lukasdev.lanchonete.Dto.ResponseDto;

import com.lukasdev.lanchonete.Enums.statusEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoResponse {

    private Long id;

    private BigDecimal total;

    private statusEnum status;

}
