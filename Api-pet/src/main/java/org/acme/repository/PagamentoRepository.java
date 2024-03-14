package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Pagamento;

import java.util.List;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {
    public List<Pagamento> findByTipo(String tipo) {
        return find("UPPER(tipo) LIKE UPPER(?1) ", "%"+tipo+"%").list();
    }

}
