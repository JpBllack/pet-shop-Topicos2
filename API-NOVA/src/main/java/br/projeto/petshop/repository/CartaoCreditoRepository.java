package br.projeto.petshop.repository;

import br.projeto.petshop.model.CartaoCredito;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CartaoCreditoRepository implements PanacheRepository<CartaoCredito>{

    
}
