package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


import org.acme.model.Endereco;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByUsuario(String id) {
        return find("UPPER(usuario_endereco) LIKE UPPER(?1) ", "%"+id+"%").list();
    }
}
