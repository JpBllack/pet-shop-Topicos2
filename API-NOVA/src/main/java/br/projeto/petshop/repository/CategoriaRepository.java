package br.projeto.petshop.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import br.projeto.petshop.model.Categoria;

import java.util.List;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {
    public List<Categoria> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }
}
