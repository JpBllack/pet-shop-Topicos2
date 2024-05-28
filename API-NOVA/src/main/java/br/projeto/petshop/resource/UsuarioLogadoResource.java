package br.projeto.petshop.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.projeto.petshop.service.PetService;
import br.projeto.petshop.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
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
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.dto.UpdateSenhaDTO;
import br.projeto.petshop.dto.UsernameDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.model.Endereco;

@Path("/usuarioLogado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {
    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService userService;
    
    @Inject
    PetService petService;

    /* @Inject
    EnderecoService enderecoService; */

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUsuario() {
        try {
            String login = jwt.getSubject();
            LOG.info("E-mail extraído do token JWT: " + login);

            UsuarioResponseDTO user = userService.findByEmail(login);
            return Response.ok(user).build();
        } catch (Exception e) {
            LOG.error("Erro ao buscar usuario");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }

    }

    // ---------- completar dados ----------

    @PATCH
    @Path("/update/cpf")
    @RolesAllowed({ "User", "Admin" })
    public Response updateCpf(CpfDTO cpfDTO) {

        String login = jwt.getSubject();

        try {
            LOG.info("Inserindo CPF");
            userService.updateCPF(login, cpfDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao inserir cpf");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    // ---------- Updates ----------

    @PATCH
    @Path("/update/nome")
    @RolesAllowed({ "User", "Admin" })
    public Response updateNome(NomeDTO nomeDTO) {

        String login = jwt.getSubject();

        try {
            LOG.info("Inserindo CPF");
            userService.updateNome(login, nomeDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao inserir cpf");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/email/")
    @RolesAllowed({ "User", "Admin" })
    public Response updateEmail(EmailDTO newEmail) {

        String login = jwt.getSubject();

        try {
            LOG.info("Alterando o email");
            userService.updateEmail(login, newEmail);
            return Response.noContent().build();
        } catch (ValidationException e) {
            LOG.error("Email.não alterado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/username/")
    @RolesAllowed({ "User", "Admin" })
    public Response updateUsername(UsernameDTO newUsername) {

        String login = jwt.getSubject();

        try {
            LOG.info("Alterando o username");
            userService.updateUsername(login, newUsername);
            return Response.noContent().build();
        } catch (ValidationException e) {
            LOG.error("Username não alterado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/password/")
    @RolesAllowed({ "User", "Admin" })
    public Response updateSenha(UpdateSenhaDTO updateSenha) {

        String login = jwt.getSubject();

        try {
            LOG.info("Alterando a senha");
            userService.updateSenha(login, updateSenha);
            return Response.noContent().build();
        } catch (ValidationException e) {
            LOG.info("Valor invalido");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch (ForbiddenException e) {
            LOG.info("Acesso negado");
            e.printStackTrace();
            Error error = new Error("403", e.getMessage());
            return Response.status(Status.FORBIDDEN).entity(error).build();
        }
    }

    @POST
    @Path("/insert/pet")
    @RolesAllowed({"User", "Admin"})
    @Transactional
    public Response insertPet(PetDTO dto) {
        try {
            // Extrair email do token JWT
            String email = jwt.getSubject();
            
            // Buscar usuário pelo email
            UsuarioResponseDTO user = userService.findByEmail(email);
            if (user == null) {
                throw new ValidationException("400", "Usuário não encontrado");
            }
            
            Long userId = user.id(); // Supondo que UsuarioResponseDTO tenha um método getId()
            PetResponseDTO petResponseDTO = petService.insert(dto, userId);
            return Response.status(Status.CREATED).entity(petResponseDTO).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o pet");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @GET
    @Path("/search/pet")
    @RolesAllowed({"User", "Admin", "Vet"})
    public Response getPets(){
        try{
            String email = jwt.getSubject();

            UsuarioResponseDTO user = userService.findByEmail(email);
            Long userId = user.id();
            return  Response.ok(petService.getByUser(userId)).build();
        } catch(NotFoundException e){
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }


   /*  @POST
    @Path("/insert/endereco")
    @RolesAllowed({"User", "Admin"})
    @Transactional
    public Response insertEndereco(Endereco endereco) {
        try {
            // Extrair email do token JWT
            String email = jwt.getSubject();
            
            // Buscar usuário pelo email
            UsuarioResponseDTO user = userService.findByEmail(email);
            if (user == null) {
                throw new ValidationException("400", "Usuário não encontrado");
            }
            
            Long userId = user.id(); // Supondo que UsuarioResponseDTO tenha um método getId()
            PetResponseDTO petResponseDTO = enderecoService.insert(endereco, userId);
            return Response.status(Status.CREATED).entity(petResponseDTO).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o pet");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    } */

    

}
