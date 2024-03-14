package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.ItemVenda;

@ApplicationScoped
public class ItemVendaRepository implements PanacheRepository<ItemVenda> {

}
