package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.CartaoCreditoDTO;
import br.projeto.petshop.dto.CartaoCreditoResponseDTO;
import br.projeto.petshop.model.CartaoCredito;
import br.projeto.petshop.model.ValidadeCartao;
import br.projeto.petshop.repository.CartaoCreditoRepository;
import br.projeto.petshop.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CartaoCreditoServiceImpl implements CartaoCreditoService{
    
    @Inject
    private CartaoCreditoRepository cartaoRepository;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Override
    public List<CartaoCreditoResponseDTO> getAllByUser(Long id) {
        return usuarioRepository.findCartoesByUsuario(id).stream().map(e -> CartaoCreditoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public CartaoCreditoResponseDTO getById(Long id) {
        return CartaoCreditoResponseDTO.valueOf(cartaoRepository.findById(id));
    }

    @Override
    @Transactional
    public CartaoCreditoResponseDTO insert(CartaoCreditoDTO dto, Long idUser) {
        
        CartaoCredito newCartao = new CartaoCredito();
        ValidadeCartao validade = new ValidadeCartao();

        newCartao.setNumeroCartao(dto.numero());
        newCartao.setCodigoSeguranca(dto.codigoSeguranca());
        validade.setMes(dto.mesValidade());
        validade.setAno(dto.anoValidade());
        newCartao.setValidade(validade);
        newCartao.setUsuario(usuarioRepository.findById(idUser));

        cartaoRepository.persist(newCartao);

        return CartaoCreditoResponseDTO.valueOf(newCartao);
    }

    @Override
    @Transactional
    public CartaoCreditoResponseDTO update(CartaoCreditoDTO dto, Long idCartao){

        CartaoCredito cartaoUpdate = cartaoRepository.findById(idCartao);
        ValidadeCartao validadeUpdate = cartaoRepository.findById(idCartao).getValidade();

        cartaoUpdate.setNumeroCartao(dto.numero());
        cartaoUpdate.setCodigoSeguranca(dto.codigoSeguranca());
        validadeUpdate.setMes(dto.mesValidade());
        validadeUpdate.setAno(dto.anoValidade());
        cartaoUpdate.setValidade(validadeUpdate);

        return CartaoCreditoResponseDTO.valueOf(cartaoUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!cartaoRepository.deleteById(id)){
            throw new NotFoundException("Cartão não encontrado");
        }
    }

    
    
}
