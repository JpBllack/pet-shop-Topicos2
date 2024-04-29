package br.projeto.petshop.repository;

import br.projeto.petshop.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca>{
    public Marca findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").firstResult();
    }

    //verificar se já existe
    public boolean existsByNome(String nome) {
        return find("nome", nome).count() > 0;
    }
}
