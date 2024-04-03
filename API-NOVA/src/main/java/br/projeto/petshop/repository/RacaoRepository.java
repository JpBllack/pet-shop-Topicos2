package br.projeto.petshop.repository;

import br.projeto.petshop.model.Racao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RacaoRepository implements PanacheRepository<Racao> {
    public List<Racao> findBySabor(String sabor) {
        return list("sabor", sabor);
    }
    
    public boolean existsBySabor(String sabor) {
        return find("sabor", sabor).firstResultOptional().isPresent();
    }
}
