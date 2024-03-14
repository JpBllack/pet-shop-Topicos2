package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Categoria;
import org.acme.model.Usuario;

import java.util.List;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {
    public List<Categoria> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }
}
