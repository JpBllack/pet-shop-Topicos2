package br.projeto.petshop.repository;

import java.util.List;

import br.projeto.petshop.model.Compra;
import br.projeto.petshop.model.ItemCompra;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CompraRepository implements PanacheRepository<Compra> {

    @Inject
    ItemCompraRepository itemCompraRepository;

    public List<Compra> findByUserId(Long id){
        return find("usuarioId", id).list();
    }
    
    public List<ItemCompra> findItensByCompraId(Long compraId) {
        return itemCompraRepository.find("compra.id", compraId).list();
    }
}
