package br.projeto.petshop.repository;


import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.TipoAnimal;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class TipoAnimalRepository implements PanacheRepository<TipoAnimal>{


    @PersistenceContext
    private EntityManager entityManager;

    public TipoAnimal findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").firstResult();
    }
    
    //verificar se já existe
    public boolean existsByNome(String nome) {
        return find("nome", nome).count() > 0;
    }



}
