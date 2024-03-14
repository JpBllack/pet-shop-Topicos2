package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AvaliacaoResponseDTO;
import org.acme.dto.ProdutoDTO;
import org.acme.dto.ProdutoResponseDTO;
import org.acme.dto.UsuarioResponseDTO;
import org.acme.dto.ProdutoDTO;
import org.acme.form.ImageForm;
import org.acme.service.ProdutoService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.IOException;
import java.util.List;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    @Inject
    ProdutoService service;

    @GET
    @PermitAll
    public List<ProdutoResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @PermitAll
    @Path("/{id}")
    public ProdutoResponseDTO getId(@PathParam("id") Long id){
        return service.getId(id);
    }

    @GET
    @PermitAll
    @Path("/avaliacao/{id}")
    public List<AvaliacaoResponseDTO> getAvaliacao(@PathParam("id") Long id){
        return service.getAvaliacao(id);
    }

    @GET
    @PermitAll
    @Path("/nome/{nome}")
    public List<ProdutoResponseDTO> getNome(@PathParam("nome") String nome){
        return service.getNome(nome);
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(ProdutoDTO enderecoDTO){
        return service.insert(enderecoDTO);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response update(@PathParam("id") long id, ProdutoDTO produto){
        return service.update(id, produto);
    }
    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/retiraestoque/{id}/{quantidade}")
    public Response retiraEstoque(@PathParam("id") Long id, @PathParam("quantidade") int quantidade){
        return service.retiraEstoque(id, quantidade);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/adicionaestoque/{id}/{quantidade}")
    public Response adicionaEstoque(@PathParam("id") Long id, @PathParam("quantidade") int quantidade){
        return service.adicionaEstoque(id, quantidade);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/imagem/salvar/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form, @PathParam("id") Long produtoId){
        return service.salvarImagem(form, produtoId);
    }

    @PATCH
    @PermitAll
    @Path("/imagem/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImagem(Long produtoId){
        return service.downloadImagem(produtoId);
    }
}
