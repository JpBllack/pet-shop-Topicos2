package br.projeto.petshop.repository;

import java.util.List;

import br.projeto.petshop.model.Compra;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompraRepository implements PanacheRepository<Compra> {

    public List<Compra> findByUserId(Long id){
        return find("usuarioId", id).list();
    }
    
}
