package br.projeto.petshop.service;

import br.projeto.petshop.dto.MunicipioDTO;
import br.projeto.petshop.dto.MunicipioResponseDTO;

import java.util.List;

public interface MunicipioService {


    List<MunicipioResponseDTO> getAll();

  
    MunicipioResponseDTO getById(long id);

   
    void insertMunicipio(MunicipioDTO municipioDTO);

    
    void updateMunicipio(long id, MunicipioDTO municipioDTO);

   
    void deleteMunicipio(long id);
}
