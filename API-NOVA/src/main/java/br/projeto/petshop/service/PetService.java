package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;

public interface PetService {

    PetResponseDTO insert(PetDTO petDTO, Long userId);

    PetResponseDTO update(long id, PetDTO dto);

    void delete(long id);

    List<PetResponseDTO> getAll();

    PetResponseDTO getByNome(String nome);

    PetResponseDTO getById(long id);

    public List<PetResponseDTO> getByUser(Long userId);

    /* PetResponseDTO changeImage(long id, String imageName); */
}
