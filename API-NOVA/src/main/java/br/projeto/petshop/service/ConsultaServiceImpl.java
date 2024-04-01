package br.projeto.petshop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.ConsultaDTO;
import br.projeto.petshop.model.Consulta;
import br.projeto.petshop.model.Veterinario;
import br.projeto.petshop.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ConsultaServiceImpl implements ConsultaService {
    
    public static final Logger LOG = Logger.getLogger(ConsultaServiceImpl.class);

    @Inject
    ConsultaRepository consultaRepository;
    
    @Override
    @Transactional
    public Response criarConsulta(ConsultaDTO consultaDTO) {
        try {
            LOG.info("Requisição insert de consulta()");
    
            Consulta consulta = new Consulta();
            consulta.setData(consultaDTO.data());
            consulta.setMotivo(consultaDTO.motivo());
            
            // Supondo que o ID do veterinário seja uma String
            Veterinario veterinario = new Veterinario();
            veterinario.setId(consultaDTO.veterinario());
            consulta.setVeterinario(veterinario);
       
            consultaRepository.persist(consulta);
    
            return Response.ok(consultaDTO).build();
        } catch (Exception e) {
            LOG.error("Erro ao processar requisição insert de consulta", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    

    @Override
    public List<ConsultaDTO> buscarTodasConsultas() {
        return consultaRepository.listAll().stream()
                .map(consulta -> new ConsultaDTO(
                    consulta.getData(),
                    consulta.getMotivo(),
                    consulta.getVeterinario().getId(),
                    consulta.getPet().getId()
                ))
                .collect(Collectors.toList());
    }
    


    @Override
    @Transactional
    public Response atualizarConsulta(ConsultaDTO consultaDTO, long id) {
        try {
            LOG.info("Atualizando consulta com ID: {}");

            Consulta consulta = consultaRepository.findById(id);
            if (consulta == null) {
                LOG.warn("Consulta com ID {} não encontrada.");
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            // Atualiza os campos da consulta com os dados do DTO
            consulta.setData(consultaDTO.data());
            consulta.setMotivo(consultaDTO.motivo());
            // Supondo que o ID do veterinário seja uma String
            Veterinario veterinario = new Veterinario();
            veterinario.setId(consultaDTO.veterinario());
            consulta.setVeterinario(veterinario);

            consultaRepository.persist(consulta);

            LOG.info("Consulta com ID {} atualizada com sucesso.");
            return Response.ok(consultaDTO).build();
        } catch (Exception e) {
            LOG.error("Erro ao atualizar consulta com ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Override
    public Response buscarConsultaPorId(long id) {
        LOG.info("Buscando consulta com ID: {}");
    
        Consulta consulta = consultaRepository.findById(id);
        if (consulta == null) {
            LOG.warn("Consulta com ID {} não encontrada.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    
        // Cria o ConsultaDTO diretamente aqui
        ConsultaDTO consultaDTO = new ConsultaDTO(
            consulta.getData(),
            consulta.getMotivo(),
            consulta.getVeterinario().getId(),
            consulta.getPet().getId()
        );
    
        LOG.info("Consulta com ID {} encontrada.");
        return Response.ok(consultaDTO).build();
    }
    

    @Override
    @Transactional
    public Response deletarConsulta(long id) {
        try {
            LOG.info("Deletando consulta com ID: {}");

            Consulta consulta = consultaRepository.findById(id);
            if (consulta == null) {
                LOG.warn("Consulta com ID {} não encontrada.");
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            consultaRepository.delete(consulta);

            LOG.info("Consulta com ID {} deletada com sucesso.");
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao deletar consulta com ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
