package org.acme.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.AvaliacaoResponseDTO;
import org.acme.dto.ProdutoDTO;
import org.acme.dto.ProdutoResponseDTO;
import org.acme.form.ImageForm;
import org.acme.model.Produto;
import org.acme.repository.CategoriaRepository;
import org.acme.repository.ProdutoRepository;
import org.acme.service.FileService;
import org.acme.service.ProdutoService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    public static final Logger LOG = Logger.getLogger(ProdutoServiceImpl.class);

    @Inject
    ProdutoRepository repository;

    @Inject
    FileService fileService;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    public List<ProdutoResponseDTO> getAll() {

        try {
            LOG.info("Requisição Produto.getAll()");

            return repository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(produto -> produto.getId()))
                    .map(produto -> new ProdutoResponseDTO(produto))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getAll()");
            return null;
        }

    }

    @Override
    public List<AvaliacaoResponseDTO> getAvaliacao(Long id) {
        return repository.findById(id).getAvaliacaoList().stream().map(AvaliacaoResponseDTO::new).collect(Collectors.toList());
    }


    @Override
    public ProdutoResponseDTO getId(Long id) {
        try {
            LOG.info("Requisição Produto.getId()");

            return new ProdutoResponseDTO(repository.findById(id));
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getId()");
            return null;
        }

    }

    @Override
    public List<ProdutoResponseDTO> getNome(String nome) {
        try {
            LOG.info("Requisição Produto.getNome()");

            return repository.findByNome(nome)
                    .stream()
                    .map(produto -> new ProdutoResponseDTO(produto))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getNome()");
            return null;
        }

    }

    @Override
    @Transactional
    public Response insert(ProdutoDTO dto) {
        try {
            Produto p = new Produto();
            p.setNome(dto.nome());
            p.setDescricao(dto.descricao());
            p.setValor(dto.valor());
            p.setCategoria(categoriaRepository.findById(dto.idCategoria()));
            p.setQuantEstoque(dto.quantEstoque());
            LOG.info("Requisição Produto.insert()");
            repository.persist(p);
            return Response.ok(new ProdutoResponseDTO(p)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }

    }

    @Override
    @Transactional
    public Response update(Long id, ProdutoDTO produto) {
        try {
            LOG.info("Requisição Produto.update()");

            Produto entity = repository.findById(id);
            if(produto.nome() != null)
                entity.setNome(produto.nome());
            if(produto.descricao() != null)
                entity.setDescricao(produto.descricao());
            if(produto.valor() != null)
                entity.setValor(produto.valor());
            return Response.ok(new ProdutoResponseDTO(entity)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.update()");
            return Response.notModified(e.getMessage()).build();
        }

    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Produto.delete()");

            Produto entity = repository.findById(id);
            repository.delete(entity);

            return Response.status(Status.OK).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.delete() -" + e.getMessage());
            return Response.notModified(e.getMessage()).build();
        }

    }

    @Override
    @Transactional
    public Response retiraEstoque(Long id, int quantidade) {
        try {

            Produto entity = repository.findById(id);
            Integer quantidadeTeste = entity.getQuantEstoque() - quantidade;
            if (quantidadeTeste < 0) {
                return Response.status(Status.CONFLICT).build();
            } else if (quantidadeTeste >= 0) {
                entity.setQuantEstoque(entity.getQuantEstoque() - quantidade);
            } else {
                return Response.status(Status.CONFLICT).build();
            }

            LOG.info("Requisição Produto.retiraEstoque()");

            return Response.status(Status.OK).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.retiraEstoque()");
            return Response.notModified(e.getMessage()).build();
        }

    }

    @Override
    @Transactional
    public Response adicionaEstoque(Long id, int quantidade) {
        try {
            Produto entity = repository.findById(id);
            if (quantidade <= 0) {
                return Response.status(Status.CONFLICT).build();
            }
            try {
                entity.setQuantEstoque(entity.getQuantEstoque() + quantidade);
            } catch (Exception e) {
                return Response.status(Status.CONFLICT).build();
            }

            LOG.info("Requisição Produto.adicionaEstoque()");

            return Response.status(Status.OK).build();

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.adicionaEstoque()");
            return Response.notModified(e.getMessage()).build();
        }

    }

    @Override
    @Transactional
    public Response salvarImagem(@MultipartForm ImageForm form, Long produtoId) {
        String nomeImagem = "";

        try {
            nomeImagem = fileService.salvarImagemProduto(form.getImagem(), form.getNome());

            Produto p = repository.findById(produtoId);
            p.setImagem(nomeImagem);

            LOG.info("Requisição Produto.salvarImagem()");

            return Response.ok(new ProdutoResponseDTO(p)).build();
        } catch (IOException e) {

            LOG.error("Erro ao rodar Requisição Produto.salvarImagem()");

            return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
        }

    }

    @Override
    public Response downloadImagem(Long produtoId) {
        try {
            Produto p = new Produto();
            p = repository.findById(produtoId);
            if(p != null){
                if(p.getImagem() != null){
                    return Response.ok(fileService.download(p.getImagem())).build();
                }else{
                    throw new Exception("Produto não contém imagem");
                }
            }else{
                throw new Exception("Produto não encontrado!");
            }
        }catch (Exception e){
                return Response.notModified(e.getMessage()).build();
        }

    }
}