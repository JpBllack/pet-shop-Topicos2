package br.projeto.petshop.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.projeto.petshop.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.ForbiddenException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.application.Error;
import br.projeto.petshop.dto.CpfDTO;
import br.projeto.petshop.dto.EmailDTO;
import br.projeto.petshop.dto.NomeDTO;
import br.projeto.petshop.dto.UpdateSenhaDTO;
import br.projeto.petshop.dto.UsernameDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;

@Path("/usuarioLogado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {
    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService userService;


    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @GET
    @Path("/user")
    //@RolesAllowed({ "User", "Admin" })
    public Response getUser() {
        try{
            String login = jwt.getSubject();
            
            UsuarioResponseDTO user = userService.findByEmail(login);
            return Response.ok(user).build();
        } catch(Exception e){
            LOG.error("Erro ao buscar usuario");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }

       
    }


    // ---------- completar dados ----------

    
    @PATCH
    @Path("/update/cpf")
    @RolesAllowed({"User", "Admin"})
    public Response updateCpf(CpfDTO cpfDTO){

        String login = jwt.getSubject();

        try{
            LOG.info("Inserindo CPF");
            userService.updateCPF(login, cpfDTO);
            return Response.noContent().build();
        } catch(Exception e){
            LOG.error("Erro ao inserir cpf");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/nome")
    @RolesAllowed({"User", "Admin"})
    public Response updateNome(NomeDTO nomeDTO){

        String login = jwt.getSubject();

        try{
            LOG.info("Inserindo CPF");
            userService.updateNome(login, nomeDTO);
            return Response.noContent().build();
        } catch(Exception e){
            LOG.error("Erro ao inserir cpf");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    // ---------- Updates ----------

    @PATCH
    @Path("/update/email/")
    @RolesAllowed({"User", "Admin"})
    public Response updateEmail(EmailDTO newEmail){

        String login = jwt.getSubject();

        try{
            LOG.info("Alterando o email");
            userService.updateEmail(login, newEmail);
            return Response.noContent().build();
        } catch(ValidationException e){
            LOG.error("Email.não alterado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/username/")
    @RolesAllowed({"User", "Admin"})
    public Response updateUsername(UsernameDTO newUsername){

        String login = jwt.getSubject();

        try{
            LOG.info("Alterando o username");
            userService.updateUsername(login, newUsername);
            return Response.noContent().build();
        } catch(ValidationException e){
            LOG.error("Username não alterado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/password/")
    @RolesAllowed({"User", "Admin"})
    public Response updateSenha(UpdateSenhaDTO updateSenha){

        String login = jwt.getSubject();

        try{
            LOG.info("Alterando a senha");
            userService.updateSenha(login, updateSenha);
            return Response.noContent().build();
        } catch(ValidationException e){
            LOG.info("Valor invalido");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch(ForbiddenException e){
            LOG.info("Acesso negado");
            e.printStackTrace();
            Error error = new Error("403", e.getMessage());
            return Response.status(Status.FORBIDDEN).entity(error).build();
        }
    }
}
