package br.projeto.petshop.repository;

import br.projeto.petshop.dto.MunicipioResponseDTO;
import br.projeto.petshop.model.Municipio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio>{

    @PersistenceContext
    private EntityManager entityManager;

    
    public Municipio findById(Long id) {
        return entityManager.find(Municipio.class, id);
    }

    public List<Municipio> findByEstadoId(Long estadoId){
        return find("estado.id", estadoId).list();
    }

    public List<Municipio> listAll() {
        return entityManager.createQuery("SELECT m FROM Municipio m", Municipio.class).getResultList();
    }

  
    public void persistMunicipio(Municipio municipio) {
        entityManager.persist(municipio);
    }

    
    public void updateMunicipio(Municipio municipio) {
        if (entityManager.contains(municipio)) {
            entityManager.merge(municipio);
        } else {
            persistMunicipio(municipio);
        }
    }

   
    public boolean deleteById(Long id) {
        Municipio municipio = findById(id);
        if (municipio != null) {
            entityManager.remove(municipio);
            return true;
        }
        return false;
    }
}
