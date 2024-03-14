package org.acme.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.dto.CidadeDTO;
import org.acme.dto.CidadeResponseDTO;
import org.acme.model.Categoria;
import org.acme.model.Cidade;
import org.acme.model.Estado;
import org.acme.repository.CidadeRepository;
import org.acme.repository.EstadoRepository;
import org.acme.service.CidadeService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService{
    public static final Logger LOG = Logger.getLogger(CidadeServiceImpl.class);
    @Inject
    CidadeRepository repository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public Response insert(CidadeDTO dtoCidade) {

        try {
            LOG.info("Requisição insert()");
            Cidade novaCidade = new Cidade();
            novaCidade.setNome(dtoCidade.nome());
            Estado e = new Estado();
            e = estadoRepository.findById(dtoCidade.idEstado());
            novaCidade.setEstado(e);
            if(novaCidade.getEstado().getId() != null){ //EXEMPLO DE CRIAÇÃO DE EXCEÇÃO (SEM NOTAÇÃO NO ATRIBUTO - MODEL)
            repository.persist(novaCidade);

            return Response.ok(new CidadeResponseDTO(novaCidade)).build();
            }else{
                
                throw new Exception("Estado não pode ser nulo");
            }

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }
        
    }

    @Override
    @Transactional
    public CidadeResponseDTO update(CidadeDTO cidadeDTO, Long id) {
        
        Cidade cidade = repository.findById(id);
        cidade.setNome(cidadeDTO.nome());
        cidade.setEstado(estadoRepository.findById(cidadeDTO.idEstado()));
        
        return new CidadeResponseDTO (cidade);
    }

    @Override
    @Transactional //Anuncia mudança no banco de dados
    public Response delete(Long id) {


        try {
            LOG.info("Requisição delete()");
            Cidade cidade = repository.findById(id);
            if (cidade != null) {
                repository.delete(cidade);
                return Response.ok().build();
            } else {
                throw new Exception("não encontrado!");
            }
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição delete()");
            return Response.notModified(e.getMessage()).build();
        }

    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        try {
            LOG.info("Requisição getId()");
            return new CidadeResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }

       }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) { //Retorno de dados usa o ResponseDTO, DTO inserção de dados.
        return repository.findByNome(nome).stream().map(cidade -> new CidadeResponseDTO(cidade)).collect(Collectors.toList());
        //stream gera a copia da lista, map permite o uso da lambda que transforma cidade em cidadeResponse e o collect transforma o objeto em lista.
    }

    @Override
    public List<CidadeResponseDTO> findAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream().map(cidade -> new CidadeResponseDTO(cidade)).collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }
    
}
