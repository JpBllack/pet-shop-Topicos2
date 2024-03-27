package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.*;
import org.acme.service.VeterinarioService;

import java.util.List;

@Path("/veterinario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeterinarioResource {
    @Inject
    VeterinarioService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<VeterinarioResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public VeterinarioResponseDTO getId(@PathParam("id") String id){
        return service.getId(id);
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/nome/{nome}")
    public List<VeterinarioResponseDTO> getNome(@PathParam("nome") String nome){
        return service.getNome(nome);
    }

    @POST
    @PermitAll
    public Response insert(VeterinarioDTO dto){
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
    public VeterinarioResponseDTO getCpf(@PathParam("cpf")String cpf){
        return service.getCpf(cpf);
    }

}
