package br.projeto.petshop.service;

import br.projeto.petshop.dto.ConsultaDTO;
import br.projeto.petshop.dto.ConsultaResponseDTO;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ConsultaService {

    public List<ConsultaResponseDTO> buscarTodasConsultas();

    public ConsultaResponseDTO criarConsulta(ConsultaDTO consultaDTO);

    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaDTO consultaDTO);

    public ConsultaResponseDTO buscarConsultaPorId(long id);

    public void deletarConsulta(long id);
}
