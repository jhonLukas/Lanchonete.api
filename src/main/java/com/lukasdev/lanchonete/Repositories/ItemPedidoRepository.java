package com.lukasdev.lanchonete.Repositories;

import com.lukasdev.lanchonete.Entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido , Long> {


}
