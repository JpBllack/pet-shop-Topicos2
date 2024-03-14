package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.*;
import org.acme.service.UsuarioService;

import java.util.List;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    @Inject
    UsuarioService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<UsuarioResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public UsuarioResponseDTO getId(@PathParam("id") String id){
        return service.getId(id);
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/nome/{nome}")
    public List<UsuarioResponseDTO> getNome(@PathParam("nome") String nome){
        return service.getNome(nome);
    }

    @POST
    @PermitAll
    public Response insert(UsuarioDTO dto){
        try {
            
        return service.insert(dto);
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") String id){
        return service.delete(id);
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/cpf/{cpf}")
    public UsuarioResponseDTO getCpf(@PathParam("cpf")String cpf){
        return service.getCpf(cpf);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/update/email/{id}")
    public UsuarioResponseDTO updateEmail(@PathParam("id") String id, UsuarioUpdateEmailDTO email){
        return service.updateEmail(id, email);
    }
    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/update/nome/{id}")
    public UsuarioResponseDTO updateNome(@PathParam("id") String id, UsuarioUpdateNomeDTO nome){
        return service.updateNome(id, nome);
    }
    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/update/login/{id}")
    public UsuarioResponseDTO updateLogin(@PathParam("id") String id, UsuarioUpdateLoginDTO login){
        return service.updateLogin(id, login);
    }
    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/update/senha/{id}")
    public UsuarioResponseDTO updateSenha(@PathParam("id") String id, UsuarioUpdateSenhaDTO senha){
        return service.updateSenha(id, senha);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/promoveradmin/{id}")
    public Response promoverAdmin(@PathParam("id") String id){
        return service.promoverAdmin(id);
    }
}
