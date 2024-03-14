package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Venda;

@ApplicationScoped
public class VendaRepository implements PanacheRepository<Venda> {

}
