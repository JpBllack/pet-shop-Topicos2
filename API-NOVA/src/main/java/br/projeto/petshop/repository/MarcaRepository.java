package br.projeto.petshop.repository;

import br.projeto.petshop.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca>{

    @PersistenceContext
    private EntityManager entityManager;

    public Marca findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").firstResult();
    }

    //verificar se já existe
    public boolean existsByNome(String nome) {
        return find("nome", nome).count() > 0;
    }


    public boolean isBeingReferencedByAnotherEntity(Long id) {
        // Verifique se a marca está sendo referenciada por outra entidade (por exemplo, rações)
        // Você pode usar uma consulta SQL ou usar JPA/Hibernate para verificar referências
        
        // Vamos supor que você tenha uma entidade Racao com uma relação com Marca
        // E você quer verificar se a marca está sendo usada por rações
        long count = entityManager.createQuery("SELECT COUNT(r) FROM Racao r WHERE r.marca.id = :marcaId", Long.class)
            .setParameter("marcaId", id)
            .getSingleResult();
        
        // Se o count for maior que zero, significa que a marca está sendo referenciada
        return count > 0;
    }
    


}
