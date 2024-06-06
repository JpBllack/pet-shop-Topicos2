package br.projeto.petshop.repository;

import br.projeto.petshop.model.Racao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class RacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Racao findById(Long id) {
        return entityManager.find(Racao.class, id);
    }

    public List<Racao> listAll() {
        return entityManager.createQuery("SELECT r FROM Racao r", Racao.class).getResultList();
    }

    public List<Racao> findByNome(String nome) {
        return entityManager.createQuery("SELECT r FROM Racao r WHERE UPPER(r.nome) LIKE UPPER(:nome) OR UPPER(r.sabor) LIKE UPPER(:nome)", Racao.class)
                            .setParameter("nome", "%" + nome.toUpperCase() + "%")
                            .getResultList();
    }
    
    
    public List<Racao> findBySabor(String sabor) {
        return entityManager.createQuery("SELECT r FROM Racao r WHERE UPPER(r.sabor) = UPPER(:sabor)", Racao.class)
                            .setParameter("sabor", sabor.toUpperCase())
                            .getResultList();
    }
    
    
    public void persist(Racao racao) {
        entityManager.persist(racao);
    }

    public void update(Racao racao) {
        if (entityManager.contains(racao)) {
            entityManager.merge(racao);
        } else {
            persist(racao);
        }
    }

    public boolean deleteById(Long id) {
        Racao racao = findById(id);
        if (racao != null) {
            entityManager.remove(racao);
            return true;
        }
        return false;
    }

    public List<Racao> findByAnimalNome(long animalId) {
        return entityManager.createQuery("SELECT r FROM Racao r WHERE r.animal.id = :animalId", Racao.class)
                            .setParameter("animalId", animalId)
                            .getResultList();
    }
}
