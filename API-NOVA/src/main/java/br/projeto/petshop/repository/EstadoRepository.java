package br.projeto.petshop.repository;

import br.projeto.petshop.model.Estado;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class EstadoRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Estado findById(Long id) {
        return entityManager.find(Estado.class, id);
    }

    public List<Estado> listAll() {
        return entityManager.createQuery("SELECT e FROM Estado e", Estado.class).getResultList();
    }

    public Estado findBySigla(String sigla) {
        try {
            return entityManager.createQuery("SELECT e FROM Estado e WHERE e.sigla = :sigla", Estado.class)
                    .setParameter("sigla", sigla)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null se nenhum estado for encontrado com a sigla especificada
        }
    }

    public void persist(Estado estado) {
        entityManager.persist(estado);
    }

    public boolean deleteById(Long id) {
        Estado estado = findById(id);
        if (estado != null) {
            entityManager.remove(estado);
            return true;
        }
        return false;
    }
}
