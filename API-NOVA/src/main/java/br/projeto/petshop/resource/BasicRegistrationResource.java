package br.projeto.petshop.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.service.UsuarioService;

import org.jboss.logging.Logger;

import br.projeto.petshop.application.Error;
import br.projeto.petshop.dto.LoginDTO;
import br.projeto.petshop.dto.UsuarioBasicoDTO;

@Path("/basicUsers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicRegistrationResource {

    @Inject
    UsuarioService userService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/insert/")
    public Response insertBasicUser(UsuarioBasicoDTO dto) {
        try {
            LOG.info("Inserindo um usuario basico");
            return Response.status(Status.CREATED).entity(userService.inserirUsuarioBasico(dto)).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o usuario basico");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

}