package br.projeto.petshop.repository;

import br.projeto.petshop.model.Estado;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Encontra um estado pelo ID
    public Estado findById(Long id) {
        return entityManager.find(Estado.class, id);
    }

    // Lista todos os estados
    public List<Estado> listAll() {
        return entityManager.createQuery("SELECT e FROM Estado e", Estado.class).getResultList();
    }

    // Persiste um novo estado no banco de dados
    public void persist(Estado estado) {
        entityManager.persist(estado);
    }

    // Atualiza um estado existente
    public void update(Estado estado) {
        if (entityManager.contains(estado)) {
            entityManager.merge(estado);
        } else {
            persist(estado);
        }
    }

    // Exclui um estado pelo ID
    public boolean deleteById(Long id) {
        Estado estado = findById(id);
        if (estado != null) {
            entityManager.remove(estado);
            return true;
        }
        return false;
    }
}
