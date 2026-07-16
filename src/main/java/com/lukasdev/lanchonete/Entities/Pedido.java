package com.lukasdev.lanchonete.Entities;


import com.lukasdev.lanchonete.Enums.statusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private statusEnum status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;


}
