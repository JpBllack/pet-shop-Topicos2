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
public class CartaoCreditoServiceImpl implements CartaoCreditoService {

    @Inject
    private CartaoCreditoRepository cartaoRepository;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Override
    public List<CartaoCreditoResponseDTO> getAllByUser(Long id) {
        return usuarioRepository.findCartoesByUsuario(id).stream().map(e -> CartaoCreditoResponseDTO.valueOf(e))
                .toList();
    }

    @Override
    public CartaoCreditoResponseDTO getById(Long id) {
        return CartaoCreditoResponseDTO.valueOf(cartaoRepository.findById(id));
    }

    @Override
    @Transactional
    public CartaoCreditoResponseDTO setPrincipal(Long id) {
        // Recupere o cartão pelo ID
        CartaoCredito cartao = cartaoRepository.findById(id);
        if (cartao == null) {
            throw new NotFoundException("Cartão não encontrado");
        }

        // Verifique se o cartão pertence ao usuário
        Long userId = cartao.getUsuario().getId();
        List<CartaoCredito> userCartoes = usuarioRepository.findCartoesByUsuario(userId);
        boolean cartaoEncontrado = false;
        for (CartaoCredito c : userCartoes) {
            if (c.getId().equals(id)) {
                cartaoEncontrado = true;
                c.setPrincipal(true);
                cartaoRepository.persist(c);
            } else {
                c.setPrincipal(false);
                cartaoRepository.persist(c);
            }
        }
        if (!cartaoEncontrado) {
            throw new NotFoundException("O cartão não pertence ao usuário");
        }

        return CartaoCreditoResponseDTO.valueOf(cartao);
    }

    @Override
    @Transactional
    public CartaoCreditoResponseDTO insert(CartaoCreditoDTO dto, Long idUser) {

        CartaoCredito newCartao = new CartaoCredito();
        ValidadeCartao validade = new ValidadeCartao();

        newCartao.setNome(dto.nome());
        newCartao.setNumeroCartao(dto.numero());
        newCartao.setCodigoSeguranca(dto.codigoSeguranca());
        validade.setMes(dto.mesValidade());
        validade.setAno(dto.anoValidade());
        newCartao.setValidade(validade);
        newCartao.setUsuario(usuarioRepository.findById(idUser));

        if (dto.isPrincipal()) {
            List<CartaoCredito> userCartoes = usuarioRepository.findCartoesByUsuario(idUser);
            for (CartaoCredito cartao : userCartoes) {
                if (!cartao.getId().equals(newCartao.getId())) {
                    cartao.setPrincipal(false);
                    cartaoRepository.persist(cartao);
                }
            }
        }

        newCartao.setPrincipal(dto.isPrincipal());

        cartaoRepository.persist(newCartao);

        return CartaoCreditoResponseDTO.valueOf(newCartao);
    }

    @Override
    @Transactional
    public CartaoCreditoResponseDTO update(CartaoCreditoDTO dto, Long idCartao) {

        CartaoCredito cartaoUpdate = cartaoRepository.findById(idCartao);
        ValidadeCartao validadeUpdate = cartaoRepository.findById(idCartao).getValidade();

        cartaoUpdate.setNome(dto.nome());
        cartaoUpdate.setNumeroCartao(dto.numero());
        cartaoUpdate.setCodigoSeguranca(dto.codigoSeguranca());
        validadeUpdate.setMes(dto.mesValidade());
        validadeUpdate.setAno(dto.anoValidade());
        cartaoUpdate.setValidade(validadeUpdate);

        if (dto.isPrincipal()) {
            Long userId = cartaoUpdate.getUsuario().getId();
            List<CartaoCredito> userCartoes = usuarioRepository.findCartoesByUsuario(userId);
            for (CartaoCredito cartao : userCartoes) {
                if (!cartao.getId().equals(cartaoUpdate.getId())) {
                    cartao.setPrincipal(false);
                    cartaoRepository.persist(cartao);
                }
            }
        }

        cartaoUpdate.setPrincipal(dto.isPrincipal());

        return CartaoCreditoResponseDTO.valueOf(cartaoUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CartaoCredito cartao = cartaoRepository.findById(id);
        if (cartao == null) {
            throw new NotFoundException("Cartão não encontrado");
        }

        // Verifica se o cartão a ser excluído é o principal
        if (cartao.isPrincipal()) {
            Long userId = cartao.getUsuario().getId();
            List<CartaoCredito> userCartoes = usuarioRepository.findCartoesByUsuario(userId);

            // Procura por outro cartão para definir como principal
            for (CartaoCredito c : userCartoes) {
                if (!c.getId().equals(id)) {
                    // Se encontrar outro cartão, define-o como principal e sai do loop
                    c.setPrincipal(true);
                    cartaoRepository.persist(c);
                    break;
                }
            }
        }

        // Exclui o cartão
        if (!cartaoRepository.deleteById(id)) {
            throw new NotFoundException("Cartão não encontrado");
        }
    }

}
