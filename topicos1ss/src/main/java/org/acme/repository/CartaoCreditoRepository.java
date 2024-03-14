package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.CartaoCredito;

@ApplicationScoped
public class CartaoCreditoRepository implements PanacheRepository<CartaoCredito> {

}
