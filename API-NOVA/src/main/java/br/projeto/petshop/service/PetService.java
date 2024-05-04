package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;

public interface PetService {

    PetResponseDTO insert(PetDTO dto);

    PetResponseDTO update(long id, PetDTO dto);

    void delete(long id);

    List<PetResponseDTO> getAll();

    PetResponseDTO getByNome(String nome);

    PetResponseDTO getById(long id);

    /* PetResponseDTO changeImage(long id, String imageName); */
}
