package br.projeto.petshop.resource;

import java.io.File;
import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.projeto.petshop.service.CartaoCreditoService;
import br.projeto.petshop.service.EnderecoService;
import br.projeto.petshop.service.PetService;
import br.projeto.petshop.service.ProdutoFileService;
import br.projeto.petshop.service.UsuarioFileService;
import br.projeto.petshop.service.UsuarioService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.ForbiddenException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.application.Error;
import br.projeto.petshop.dto.CartaoCreditoDTO;
import br.projeto.petshop.dto.CpfDTO;
import br.projeto.petshop.dto.EmailDTO;
import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.dto.EnderecoResponseDTO;
import br.projeto.petshop.dto.NomeDTO;
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.dto.UpdateSenhaDTO;
import br.projeto.petshop.dto.UsernameDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.form.ProdutoImageForm;
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

    @Inject
    EnderecoService enderecoService;

    @Inject
    CartaoCreditoService cartaoCreditoService;

    
    @Inject
    UsuarioFileService fileService;


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

    // ---------- Pets ----------

    @POST
    @Path("/insert/pet")
    @RolesAllowed({ "User", "Admin" })
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
    @RolesAllowed({ "User", "Admin", "Vet" })
    public Response getPets() {
        try {
            String email = jwt.getSubject();

            UsuarioResponseDTO user = userService.findByEmail(email);
            Long userId = user.id();
            return Response.ok(petService.getByUser(userId)).build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    // ---------- Endereço ----------

    @GET
    @Path("/search/endereco/")
    @RolesAllowed({ "User", "Admin", "Vet" })
    public Response getEnderecoByUser() {
        try{
            String email = jwt.getSubject();

            // Buscar usuário pelo email
            UsuarioResponseDTO user = userService.findByEmail(email);

            return Response.ok(enderecoService.getAllEnderecosByUser(user.id())).build();
        } catch(Exception e){
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }


    }

    @POST
    @Path("/insert/endereco")
    @RolesAllowed({ "User", "Admin", "Vet" })
    @Transactional
    public Response insertEndereco(EnderecoDTO endereco) {
        try {
            // Extrair email do token JWT
            String email = jwt.getSubject();

            // Buscar usuário pelo email
            UsuarioResponseDTO user = userService.findByEmail(email);
            if (user == null) {
                throw new ValidationException("400", "Usuário não encontrado");
            }

            Long userId = user.id();
            EnderecoResponseDTO enderecoResponseDTO = enderecoService.insert(endereco, userId);
            return Response.status(Status.CREATED).entity(enderecoResponseDTO).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o endereço");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            LOG.error("Erro ao inserir o endereço", e);
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @Path("/update/endereco/{id}")
    @RolesAllowed({ "User", "Admin", "Vet" })
    @Transactional
    public Response updateEndereco(@PathParam("id") Long id, EnderecoDTO enderecoDTO) {
        try {
            EnderecoResponseDTO enderecoAtualizado = enderecoService.update(id, enderecoDTO);
            return Response.ok(enderecoAtualizado).build();
        } catch (NotFoundException e) {
            LOG.error("Endereço não encontrado para o ID: " + id);
            e.printStackTrace();
            Error error = new Error("404", "Endereço não encontrado");
            return Response.status(Status.NOT_FOUND).entity(error).build();
        } catch (Exception e) {
            LOG.error("Erro ao atualizar o endereço");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @DELETE
    @Path("/delete/endereco/{id}")
    @RolesAllowed({ "User", "Admin" })
    @Transactional
    public Response deleteEndereco(@PathParam("id") Long id) {
        try {
            enderecoService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Endereço não encontrado para o ID: " + id);
            e.printStackTrace();
            Error error = new Error("404", "Endereço não encontrado");
            return Response.status(Status.NOT_FOUND).entity(error).build();
        } catch (Exception e) {
            LOG.error("Erro ao excluir o endereço");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/endereco/principal/{id}")
    @RolesAllowed({ "Admin", "User", "Vet" })
    public Response setEnderecoPrincipal(@PathParam("id") Long id){
        try{
            return Response.ok(enderecoService.setPrincipal(id)).build();
        } catch(Exception e){
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }


    // ---------- Cartao ----------

    @POST
    @Path("/insert/cartao")
    @RolesAllowed({ "User", "Admin", "Vet" })
    public Response insert(CartaoCreditoDTO dto) {
        String email = jwt.getSubject();

        UsuarioResponseDTO user = userService.findByEmail(email);

        try {
            return Response.status(Status.CREATED).entity(cartaoCreditoService.insert(dto, user.id())).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Path("/update/cartao/{id}")
    @RolesAllowed({ "Admin", "User", "Vet" })
    public Response update(@PathParam("id") Long id, CartaoCreditoDTO dto) {
        try {
            cartaoCreditoService.update(dto, id);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @DELETE
    @Path("/delete/cartao/{id}")
    @RolesAllowed({ "Admin", "User", "Vet" })
    public Response delete(@PathParam("id") Long id) {
        try {
            cartaoCreditoService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/cartao")
    @RolesAllowed({ "Admin", "User", "Vet" })
    public Response getCartoesUsuario() {
        try {

            String login = jwt.getSubject();

            UsuarioResponseDTO user = userService.findByEmail(login);

            return Response.ok(cartaoCreditoService.getAllByUser(user.id())).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/cartao/{id}")
    @RolesAllowed({ "Admin", "User", "Vet" })
    public Response getCartaoById(@PathParam("id") Long id) {
        try {
            return Response.ok(cartaoCreditoService.getById(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PATCH
    @Path("/update/cartao/principal/{id}")
    @RolesAllowed({ "Admin", "User", "Vet" })
    public Response setCartaoPrincipal(@PathParam("id") Long id) {
        try {
            return Response.ok(cartaoCreditoService.setPrincipal(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }


//---------------imagemUsuario--------------
   @Path("/quarkus/images/usuario")
    public class ImageResource {
    @GET
    @Path("/{imageName}")
    @Produces("image/jpeg")
    public Response getImage(@PathParam("imageName") String imageName) {
        File file = new File(System.getProperty("user.home") + "/quarkus/images/usuario/" + imageName);
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(file).build();
    }
}

@PATCH
@Path("/upload/image")
@RolesAllowed({ "Admin", "User", "Vet" })
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.TEXT_PLAIN)
public Response saveimage(@MultipartForm ProdutoImageForm form) {

    String login = jwt.getSubject();
    UsuarioResponseDTO user = userService.findByEmail(login);

    if (user == null) {
        LOG.error("Usuário não encontrado");
        return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
    }

    Long id = user.id();
    String nomeImagem = form.getNomeImagem();
    byte[] imagem = form.getImagem();

    if (nomeImagem == null || imagem == null) {
        LOG.error("Parâmetros inválidos");
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    try {
        LOG.info("Inserindo imagem");
        String imageName = fileService.save(nomeImagem, imagem);
        LOG.info("Alterando imagem do usuário");

        userService.changeImage(id, imageName);
        LOG.info("Imagem alterada");
        return Response.ok("Imagem = " + imageName).build();
    } catch (IOException e) {
        LOG.error("Erro ao inserir imagem");
        e.printStackTrace();
        Error error = new Error("409", e.getMessage());
        return Response.status(Response.Status.CONFLICT).entity(error).build();
    }
}



}
