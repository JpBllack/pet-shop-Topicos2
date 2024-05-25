package br.projeto.petshop.repository;

import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Racao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class PetRepository implements PanacheRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public Pet findById(Long id) {
        return entityManager.find(Pet.class, id);
    }

    public List<Pet> listAll() {
        return entityManager.createQuery("SELECT p FROM Pet p", Pet.class).getResultList();
    }


    
    public List<Pet> findByUsuario(Long idUsuario) {
        return entityManager.createQuery("SELECT p FROM Pet p WHERE p.usuario.id = :idUsuario", Pet.class)
                            .setParameter("idUsuario", idUsuario)
                            .getResultList();
    }

    public Pet findByNome(String nome) {
        return entityManager.createQuery("SELECT p FROM Pet p WHERE p.nome = :nome", Pet.class)
                            .setParameter("nome", nome)
                            .getResultStream()
                            .findFirst()
                            .orElse(null);
    }
    

    public void persist(Pet pet) {
        entityManager.persist(pet);
    }

    public void update(Pet pet) {
        entityManager.merge(pet);
    }

    public boolean deleteById(Long id) {
        Pet pet = findById(id);
        if (pet != null) {
            entityManager.remove(pet);
            return true;
        }
        return false;
    }
}
