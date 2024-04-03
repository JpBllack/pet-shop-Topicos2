package br.projeto.petshop.repository;

import java.util.List;

import br.projeto.petshop.model.TipoAnimal;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoAnimalRepository implements PanacheRepository<TipoAnimal>{
    public List<TipoAnimal> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }
    
}
