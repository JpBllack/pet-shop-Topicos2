package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.*;
import org.acme.model.Perfil;
import org.acme.model.Veterinario;
import org.acme.repository.VeterinarioRepository;
import org.acme.service.HashService;
import org.acme.service.VeterinarioService;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class VeterinarioServiceImpl implements VeterinarioService {

    public static final Logger LOG = Logger.getLogger(VeterinarioServiceImpl.class);

    @Inject
    VeterinarioRepository repository;

    @Inject
    HashService hash;

    @Override
    public List<VeterinarioResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream().map(VeterinarioResponseDTO::new).collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }


    @Override
    public VeterinarioResponseDTO getId(String id) {
        try {
            LOG.info("Requisição getId()");
            return new VeterinarioResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    public List<VeterinarioResponseDTO> getNome(String nome) {
        return repository.findByNome(nome).stream().map(VeterinarioResponseDTO::new).collect(Collectors.toList());
    }
    @Override
    public VeterinarioResponseDTO getCpf(String cpf) {
        return new VeterinarioResponseDTO(repository.findByCpf(cpf));
    }

    @Transactional
    @Override
    public Response insert(VeterinarioDTO dto) {

        try {
            LOG.info("Requisição insert()");

            Veterinario u = new Veterinario();
            u.setNome(dto.nome());
            if(!dto.cpf().isEmpty()){

                u.setCpf(dto.cpf());
            }
            else{
                throw new Exception("Cpf invalido!");
            }
            u.setEmail(dto.email());
            
            Set<Perfil> perfis =  new HashSet<>();
            perfis.add(Perfil.USER);
            u.setPerfis(perfis);

            Veterinario teste = new Veterinario();
            teste = repository.findByEmail(dto.email());
            if(teste != null){
                throw new Exception("veterinario com esse email ja existe");
            }

            repository.persist(u);
            return Response.ok(new VeterinarioResponseDTO(u)).build();

        } catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @Override
    public Response delete(String id) {
        try {
            LOG.info("Requisição delete()");
            Veterinario u = new Veterinario();
            u = repository.findById(id);
            repository.deleteById(u.getId());
            return Response.ok().build();
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição delete() - " + e.getMessage());
            return Response.notModified(e.getMessage()).build();
        }

    }


    @Override
    public Veterinario findByLoginAndSenha(String login, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLoginAndSenha'");
    }
}
