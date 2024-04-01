package br.projeto.petshop.service;

import br.projeto.petshop.dto.ConsultaDTO;

import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ConsultaService {
    Response criarConsulta(ConsultaDTO consultaDTO);
    List<ConsultaDTO> buscarTodasConsultas();
    Response atualizarConsulta(ConsultaDTO consultaDTO, long id);
    Response buscarConsultaPorId(long id);
    Response deletarConsulta(long id);
}
