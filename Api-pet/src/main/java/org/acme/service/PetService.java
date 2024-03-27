package org.acme.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.List;

import org.acme.dto.PetDTO;
import org.acme.model.Pet;

public interface PetService {

    List<PetDTO> buscarTodosPets();

    PetDTO buscarPetPorNome(String nome);

    PetDTO buscarPetPorId(Long id);

    Response criarPet(Pet pet);

    Response atualizarPet(PetDTO petDTO, Long id);

    Response deletarPet(Long id);

    Collection<PetDTO> getAllPets();

    Pet getPetById(Long id);

    void updatePet(Pet pet);

}
