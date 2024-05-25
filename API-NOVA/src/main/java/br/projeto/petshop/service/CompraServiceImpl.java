package br.projeto.petshop.service;

import br.projeto.petshop.model.Compra;
import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.repository.CompraRepository;
import br.projeto.petshop.resource.PetResource;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

@ApplicationScoped
public class CompraServiceImpl implements CompraService {

    @Inject
    private CompraRepository compraRepository;

    private static final Logger LOG = Logger.getLogger(PetResource.class);

    @Override
    @Transactional
    public void concluirCompra(List<ItemCompra> itensCompra) {
        Compra compra = new Compra();
        compra.setDataCompra(new Date());

        double precoTotal = calcularTotal(itensCompra);
        LOG.info(itensCompra);
        compra.setPrecoTotal(precoTotal);

        compra.setItensCompra(itensCompra);

        compraRepository.persist(compra);
    }

    private double calcularTotal(List<ItemCompra> itensCompra) {
        double total = 0.0;
        for (ItemCompra item : itensCompra) {
            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        return total;
    }
}
