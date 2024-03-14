package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.*;
import org.acme.model.Perfil;
import org.acme.model.Usuario;
import org.acme.repository.UsuarioRepository;
import org.acme.service.HashService;
import org.acme.service.UsuarioService;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    public static final Logger LOG = Logger.getLogger(UsuarioServiceImpl.class);

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hash;

    @Override
    public List<UsuarioResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }


    @Override
    public UsuarioResponseDTO getId(String id) {
        try {
            LOG.info("Requisição getId()");
            return new UsuarioResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    public Usuario findByLoginAndSenha(String login, String senha) {
        Usuario usuario = new Usuario();
        usuario = repository.findByEmailAndSenha(login, senha);
        if(usuario == null){
            usuario = repository.findByLoginAndSenha(login, senha);
        }
        return usuario;
    }

    @Override
    public List<UsuarioResponseDTO> getNome(String nome) {
        return repository.findByNome(nome).stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
    }
    @Override
    public UsuarioResponseDTO getCpf(String cpf) {
        return new UsuarioResponseDTO(repository.findByCpf(cpf));
    }


    @Override
    @Transactional
    public UsuarioResponseDTO updateEmail(String id, UsuarioUpdateEmailDTO email) {
        Usuario u = repository.findById(id);
        u.setEmail(email.email());
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(u);
        return usuarioResponseDTO;
    }

    @Override
    public UsuarioResponseDTO updateNome(String id, UsuarioUpdateNomeDTO nome) {
        Usuario u = new Usuario();
        u = repository.findById(id);
        u.setNome(nome.nome());
        
        return new UsuarioResponseDTO(u);
    }

    @Transactional
    @Override
    public UsuarioResponseDTO updateLogin(String id, UsuarioUpdateLoginDTO login) {
        Usuario u = new Usuario();
        u = repository.findById(id);
        u.setLogin(login.login());
        return new UsuarioResponseDTO(u);
    }

    @Transactional
    @Override
    public UsuarioResponseDTO updateSenha(String id, UsuarioUpdateSenhaDTO senha) {
        Usuario u = new Usuario();
        u = repository.findById(id);
        u.setSenha(hash.getHashSenha(senha.senha()));
        return new UsuarioResponseDTO(u);
    }

    @Override
    @Transactional
    public Response promoverAdmin(String id) {
        try{
            Usuario u = repository.findById(id);
            if(u.getPerfis().contains(Perfil.ADMIN)){
                return Response.ok("Usuario ja era admin").build();
            }
            u.getPerfis().add(Perfil.ADMIN);
            return Response.ok(new UsuarioResponseDTO(u)).build();
        }catch (Exception e){
            return Response.notModified(e.getMessage()).build();
        }

    }

    @Transactional
    @Override
    public Response insert(UsuarioDTO dto) {

        try {
            LOG.info("Requisição insert()");

            Usuario u = new Usuario();
            u.setNome(dto.nome());
            if(!dto.cpf().isEmpty()){

                u.setCpf(dto.cpf());
            }
            else{
                throw new Exception("Cpf invalido!");
            }
            u.setEmail(dto.email());
            u.setSenha(hash.getHashSenha(dto.senha()));
            u.setLogin(dto.login());
            
            Set<Perfil> perfis =  new HashSet<>();
            perfis.add(Perfil.USER);
            u.setPerfis(perfis);

            Usuario teste = new Usuario();
            teste = repository.findByEmail(dto.email());
            if(teste != null){
                throw new Exception("usuario com esse email ja existe");
            }

            repository.persist(u);
            return Response.ok(new UsuarioResponseDTO(u)).build();

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
            Usuario u = new Usuario();
            u = repository.findById(id);
            repository.deleteById(u.getId());
            return Response.ok().build();
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição delete() - " + e.getMessage());
            return Response.notModified(e.getMessage()).build();
        }

    }
}
