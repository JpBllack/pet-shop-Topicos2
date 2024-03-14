package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.PixPagamento;

@ApplicationScoped
public class PixPagamentoRepository implements PanacheRepository<PixPagamento> {

}
