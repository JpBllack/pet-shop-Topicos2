package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Produto;


import java.util.List;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    public List<Produto> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }
}
