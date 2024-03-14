package org.acme.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AvaliacaoResponseDTO;
import org.acme.dto.ProdutoDTO;
import org.acme.dto.ProdutoResponseDTO;
import org.acme.form.ImageForm;
import org.acme.model.Produto;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.File;
import java.util.List;

public interface ProdutoService {

    List<ProdutoResponseDTO> getAll();

    List<AvaliacaoResponseDTO> getAvaliacao(Long id);

    ProdutoResponseDTO getId(@PathParam("id") Long id);

    List<ProdutoResponseDTO> getNome(@PathParam("nome") String nome);

    Response insert(ProdutoDTO produtoDTO);

    Response delete(@PathParam("id") Long id);

    Response update(@PathParam("id") Long id, ProdutoDTO produto);

    Response retiraEstoque(@PathParam("id") Long id, int quantidade);

    Response adicionaEstoque(@PathParam("id") Long id, int quantidade);

    Response salvarImagem(@MultipartForm ImageForm form, Long produtoId);

    Response downloadImagem(Long produtoId);
}
