package br.projeto.petshop.service;

import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.List;

import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.model.Pet;

public interface PetService {

    public PetResponseDTO criarPet(PetDTO dto);

    public PetResponseDTO atualizarPet(Long id, PetDTO dto);

    public void deletarPet(Long id);

    public List<PetResponseDTO> buscarTodosPets();

    public PetResponseDTO buscarPetPorNome(String nome);

    public PetResponseDTO buscarPetPorId(Long id);

}
