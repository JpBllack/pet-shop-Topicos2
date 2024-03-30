package br.projeto.petshop.service;

import java.util.List;


import br.projeto.petshop.repository.UsuarioRepository;
import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.dto.LoginDTO;
import br.projeto.petshop.dto.LoginResponseDTO;
import br.projeto.petshop.dto.UsuarioDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Override
    @Transactional
    public LoginResponseDTO inserirUsuarioBasico(LoginDTO dto) {
        if(dto.email() == null || dto.email().isEmpty()){
            throw new ValidationException("400", "O email não pode estar em branco");
        } else if(dto.password() == null || dto.password().isEmpty()){
            throw new ValidationException("400", "A senha não pode estar em branco");
        }

        if(repository.existsByEmail(dto.email())){
            throw new ValidationException("400", "O email já existe");
        }

        Usuario newUsuario = new Usuario();

        newUsuario.setEmail(dto.email());

        newUsuario.setSenha(hashService.getHashPassword(dto.password()));

        newUsuario.setPerfil(Perfil.valueOf(1));

        repository.persist(newUsuario);

        return LoginResponseDTO.valueOf(newUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        if(dto.email() == null || dto.email().isEmpty() || dto.password() == null || dto.password().isEmpty()) {
            throw new ValidationException("400", "Email e senha não podem estar em branco");
        }

        if(Profile.valueOf(dto.profile()) == null){
            throw new ValidationException("400", "O tipo de perfil não pode ser nulo");
        }

        Usuario newUsuario = new Usuario();

        newUsuario.setUsuarioname(dto.usuarioname());
        newUsuario.setEmail(dto.email());

        newUsuario.setProfile(Profile.valueOf(dto.profile()));

        newUsuario.setPassword(hashService.getHashPassword(dto.password()));

        repository.persist(newUsuario);

        return UsuarioResponseDTO.valueOf(newUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioDTO dto) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Usuario não encontrado");
        }

        if(dto.email() == null || dto.email().isEmpty() || dto.password() == null || dto.password().isEmpty()) {
            throw new ValidationException("400", "Email e senha não podem estar em branco");
        }

        if(Profile.valueOf(dto.profile()) == null){
            throw new ValidationException("400", "O tipo de perfil não pode ser nulo");
        }

        Usuario usuario = repository.findById(id);
        usuario.setUsuarioname(dto.usuarioname());
        usuario.setEmail(dto.email());
        usuario.setPassword(hashService.getHashPassword(dto.password()));

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Usuario não encontrado");
        }
    }

    @Override
    public UsuarioResponseDTO findById(long id) {
        if(repository.findById(id) == null) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public UsuarioResponseDTO findByUsuarioname(String usuarioname) {
        if(repository.findByUsuarioname(usuarioname) == null) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return UsuarioResponseDTO.valueOf(repository.findByUsuarioname(usuarioname));

    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = repository.findByEmail(email);
        if(email == null){
            throw new ValidationException("email", "Email invalido");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByEmailAndPassword(String email, String password) {
        Usuario usuario = repository.findByEmailAndPassword(email, password);
        if (usuario == null) 
            throw new ValidationException("login", "Login ou senha inválido");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        if(repository.listAll().stream().map(e -> UsuarioResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há usuarios");
        }
        return repository.listAll().stream().map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }
    
}
