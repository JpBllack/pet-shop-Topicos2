package br.projeto.petshop.resource;

import br.projeto.petshop.application.Error;
import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;
import br.projeto.petshop.form.ProdutoImageForm;
import br.projeto.petshop.repository.RacaoRepository;
import br.projeto.petshop.service.ProdutoFileService;
import br.projeto.petshop.service.RacaoService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Path("/racoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RacaoResource {

    private static final Logger LOG = Logger.getLogger(RacaoResource.class);

    @Inject
    RacaoService racaoService;

    @Inject
    RacaoRepository racaoRepository;

    @Inject
    ProdutoFileService fileService;

    @GET
    @PermitAll
    @Transactional
    public Response getAllRacoes() {
        LOG.info("Buscando todas as rações");
        List<RacaoResponseDTO> racoes = racaoService.getAll();
        if (racoes.isEmpty()) {
            LOG.info("Nenhuma ração encontrada");
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma ração encontrada").build();
        } else {
            LOG.info("Retornando todas as rações");
            return Response.ok(racoes).build();
        }
    }


    @GET
    @Path("/{animalId}")
    public List<RacaoResponseDTO> getByAnimal(@PathParam("animalId") long animalId) {
    return racaoService.getByAnimal(animalId);
    }


    @GET
    @Path("/{id}")
    @PermitAll
    @Transactional
    public Response getRacaoById(@PathParam("id") long id) {
        LOG.info("Buscando ração pelo ID: " + id);
        try {
            RacaoResponseDTO racao = racaoService.getById(id);
            LOG.info("Ração encontrada: " + racao);
            return Response.ok(racao).build();
        } catch (NotFoundException e) {
            LOG.error("Ração não encontrada", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search/{nome}")
    @PermitAll
    @Transactional
    public Response getRacaoByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando ração pelo Nome: " + nome);
        try {
            List<RacaoResponseDTO> racoes = racaoService.getByNome(nome);
            LOG.info("Ração encontrada: ");
            return Response.ok(racoes).build();
        } catch (NotFoundException e) {
            LOG.error("Ração não encontrada", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/insert")
    @RolesAllowed("Admin")
    @Transactional
    public Response insertRacao(@Valid RacaoDTO racaoDTO) {
        LOG.info("Inserindo nova ração: " + racaoDTO);
        try {
            racaoService.insert(racaoDTO);
            LOG.info("Ração inserida com sucesso");
            return Response.status(Response.Status.CREATED).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir a ração", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @RolesAllowed("Admin")
    @Transactional
    public Response updateRacao(@PathParam("id") long id, @Valid RacaoDTO racaoDTO) {
        LOG.info("Atualizando ração com ID: " + id);
        try {
            racaoService.update(id, racaoDTO);
            LOG.info("Ração atualizada com sucesso");
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Ração não encontrada para o ID: " + id, e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (ValidationException e) {
            LOG.error("Erro de validação ao atualizar a ração com ID: " + id, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("Admin")
    @Transactional
    public Response deleteRacao(@PathParam("id") long id) {
        LOG.info("Deletando ração pelo ID: " + id);
        try {
            racaoService.delete(id);
            LOG.info("Ração deletada com sucesso");
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Ração não encontrada", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    //----------Imagem----------

    @PATCH
    @Path("/upload/image")
    @RolesAllowed({"Admin"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveimage(@MultipartForm ProdutoImageForm form) {
        Long id = form.getId();
        String nomeImagem = form.getNomeImagem();
        byte[] imagem = form.getImagem();
        
        if (id == null || nomeImagem == null || imagem == null) {
            LOG.error("Parâmetros inválidos");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    
        try {
            LOG.info("Inserindo imagem");
            String imageName = fileService.save(nomeImagem, imagem);
            LOG.info("Alterando imagem do usuário");
    
            racaoService.changeImage(id, imageName);
            LOG.info("Imagem alterada");
            return Response.ok("Imagem = " + imageName).build();
        } catch (IOException e) {
            LOG.error("Erro ao inserir imagem");
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Response.Status.CONFLICT).entity(error).build();
        }
    }

    @GET
    @Path("/download/image/{id}")
    @RolesAllowed({"Admin"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("id") Long id) {
        LOG.infof("Buscando imagem do usuario de id %s", id);
        RacaoResponseDTO racao = racaoService.getById(id);
        String imageName = racao.imagem();

        ResponseBuilder response = Response.ok(fileService.getFile(imageName));
        response.header("Content-Disposition", "attachment;filename=" + imageName);
        return response.build();
    }

    @Path("/quarkus/images/produto")
    public class ImageResource {
    @GET
    @Path("/{imageName}")
    @Produces("image/jpeg")
    public Response getImage(@PathParam("imageName") String imageName) {
        File file = new File(System.getProperty("user.home") + "/quarkus/images/produto/" + imageName);
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(file).build();
    }
}

}
