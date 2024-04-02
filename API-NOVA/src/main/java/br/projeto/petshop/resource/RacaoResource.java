package br.projeto.petshop.resource;

import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;
import br.projeto.petshop.service.RacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/racao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RacaoResource {

    @Inject
    RacaoService racaoService;

    @GET
    public List<RacaoResponseDTO> getAllRacoes() {
        return racaoService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getRacaoById(@PathParam("id") long id) {
        RacaoResponseDTO racao = racaoService.getById(id);
        if (racao != null) {
            return Response.ok(racao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response insertRacao(@Valid RacaoDTO racaoDTO) {
        Response response = racaoService.insert(racaoDTO);
        return Response.status(response.getStatus()).build();
    }

   /*  @PUT
    @Path("/{id}")
    public Response updateRacao(@PathParam("id") long id, @Valid RacaoDTO racaoDTO) {
        Response response = racaoService.update(id, racaoDTO);
        return Response.status(response.getStatus()).build();
    }
 */
    @DELETE
    @Path("/{id}")
    public Response deleteRacao(@PathParam("id") long id) {
        Response response = racaoService.delete(id);
        return Response.status(response.getStatus()).build();
    }
}
