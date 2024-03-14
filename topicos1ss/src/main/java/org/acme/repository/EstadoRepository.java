package org.acme.repository;

import java.util.List;

import org.acme.model.Estado;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado>{
    public List<Estado> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }

}
