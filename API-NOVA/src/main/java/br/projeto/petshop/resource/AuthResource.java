package br.projeto.petshop.resource;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.LoginDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.service.HashService;
import br.projeto.petshop.service.JwtService;
import br.projeto.petshop.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UserService service;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response login(@Valid LoginDTO dto) {

        LOG.infof("Iniciando a autenticação do %s", dto.email());

        String hashSenha = hashService.getHashPassword(dto.password());

        LOG.info("Hash da senha gerado.");

        LOG.debug(hashSenha);

        UsuarioResponseDTO result = service.findByEmailAndPassword(dto.email(), hashSenha);

        if(result != null){
            LOG.info("Login e senha corretos.");
        }else{
            LOG.info("Login ou senha incorretos.");
        }

        String token = jwtService.generateJwt(result);

        LOG.info("Finalizando o processo de login");

        return Response.ok().header("Authorization", token).build();
    }
  
}