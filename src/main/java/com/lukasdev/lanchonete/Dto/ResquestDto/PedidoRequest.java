package com.lukasdev.lanchonete.Dto.ResquestDto;

import com.lukasdev.lanchonete.Enums.statusEnum;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoRequest {

    @NotNull
    private BigDecimal total;
    @NotNull
    private statusEnum status;

}
