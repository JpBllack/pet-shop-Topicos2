package br.projeto.petshop.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.projeto.petshop.dto.UsuarioDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.service.UsuarioService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import br.projeto.petshop.application.Error;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UsuarioService service;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/insert/user/")
    //@RolesAllowed({"Admin"})
    public Response insert(UsuarioDTO dto){
        try{
            LOG.info("Inserindo um usuario");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch(ValidationException e){
            LOG.error("Erro ao inserir o usuario");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/update/user/{id}")
    //@RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, UsuarioDTO dto){

        try{
            LOG.infof("Update em usuario %s", dto.email());
            service.update(id, dto);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Update não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/delete/user/{id}")
    //@RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id){

        try{
            LOG.info("Deletando usuario");
            service.delete(id);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Deleção não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }
    
    @GET
    //@RolesAllowed({"Admin"})
    public Response findAll() {
        try {
            LOG.info("Buscando todos os usuarios");
            UsuarioResponseDTO[] usuarios = service.findAll().toArray(new UsuarioResponseDTO[0]);
            if (usuarios.length == 0) {
                LOG.info("Nenhum usuário encontrado");
                return Response.status(Status.NOT_FOUND).entity("Nenhum usuário encontrado").build();
            } else {
                LOG.info("Retornando todos os usuários");
                return Response.ok(usuarios).build();
            }
        } catch(NotFoundException e) {
            LOG.error("Usuarios não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }



    @GET
    //@RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando usuario de id %s", id);
            return Response.ok(service.findById(id)).build();
        } catch(NotFoundException e) {
            LOG.error("Usuario não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    //@RolesAllowed({"Admin"})
    @Path("/search/name/{username}")
    public Response findByUsername(@PathParam("username") String username){
        try{
            LOG.infof("Buscando %s", username);
            return Response.ok(service.findByUsername(username)).build();
        } catch(NotFoundException e) {
            LOG.error("Usuario não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }
}
