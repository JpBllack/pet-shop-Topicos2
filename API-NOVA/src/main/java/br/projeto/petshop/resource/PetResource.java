package br.projeto.petshop.resource;

import br.projeto.petshop.service.PetService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @Inject
    PetService petService;


    
}
