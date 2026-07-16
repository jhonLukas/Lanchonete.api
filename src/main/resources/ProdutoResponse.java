package com.lukasdev.lanchonete.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.math.BigDecimal;


@Getter
@Setter
public class ProdutoResponse {

    private Long id;
    @Supp
    private String nome;

    private BigDecimal valor;

}
