package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.CartaoCreditoDTO;
import br.projeto.petshop.dto.CartaoCreditoResponseDTO;
import br.projeto.petshop.repository.CartaoCreditoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CartaoCreditoServiceImpl implements CartaoCreditoService{
    
    @Inject
    private CartaoCreditoRepository cartaoRepository;

    @Override
    public List<CartaoCreditoResponseDTO> getAllByUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public CartaoCreditoResponseDTO getById(Long id) {
        if(cartaoRepository.findById(id) == null){
            throw new NotFoundException("cartão não encontrado");
        }
        throw new UnsupportedOperationException("Unimplemented method 'insert'"); // ---------Falta terminar
    }

    @Override
    public CartaoCreditoResponseDTO insert(CartaoCreditoDTO dto, Long idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
    
}
