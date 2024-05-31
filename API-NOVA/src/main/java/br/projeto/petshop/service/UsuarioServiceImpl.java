package br.projeto.petshop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import br.projeto.petshop.repository.UsuarioRepository;
import br.projeto.petshop.resource.AuthResource;
import br.projeto.petshop.validation.ValidationException;
import io.quarkus.logging.Log;
import io.quarkus.security.ForbiddenException;
import br.projeto.petshop.dto.CpfDTO;
import br.projeto.petshop.dto.EmailDTO;
import br.projeto.petshop.dto.LoginDTO;
import br.projeto.petshop.dto.LoginResponseDTO;
import br.projeto.petshop.dto.NomeDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;
import br.projeto.petshop.dto.UpdateSenhaDTO;
import br.projeto.petshop.dto.UsernameDTO;
import br.projeto.petshop.dto.UsuarioBasicoDTO;
import br.projeto.petshop.dto.UsuarioBasicoResponseDTO;
import br.projeto.petshop.dto.UsuarioDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Racao;
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

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @Override
    @Transactional
    public UsuarioBasicoResponseDTO inserirUsuarioBasico(UsuarioBasicoDTO dto) {
        if (dto.email() == null || dto.email().isEmpty()) {
            throw new ValidationException("email", "O email não pode estar em branco");
        } else if (dto.senha() == null || dto.senha().isEmpty()) {
            throw new ValidationException("senha", "A senha não pode estar em branco");
        } else if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ValidationException("nome", "O nome não pode estar em branco");
        } else if (dto.sobrenome() == null || dto.sobrenome().isEmpty()) {
            throw new ValidationException("sobrenome", "O sobrenome não pode estar em branco");
        }

        if (repository.existsByEmail(dto.email())) {
            throw new ValidationException("400", "O email já existe");
        }

        Usuario newUsuario = new Usuario();

        newUsuario.setNome(dto.nome());

        newUsuario.setSobrenome(dto.sobrenome());

        newUsuario.setEmail(dto.email());

        newUsuario.setSenha(hashService.getHashSenha(dto.senha()));

        newUsuario.setPerfil(Perfil.valueOf(2));

        repository.persist(newUsuario);

        return UsuarioBasicoResponseDTO.valueOf(newUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        if (dto.email() == null || dto.email().isEmpty() || dto.senha() == null || dto.senha().isEmpty()) {
            throw new ValidationException("400", "Email e senha não podem estar em branco");
        }

        if (Perfil.valueOf(dto.perfil().getId()) == null) {
            throw new ValidationException("400", "O tipo de perfil não pode ser nulo");
        }

        Usuario newUsuario = new Usuario();

        newUsuario.setNome(dto.nome());
        newUsuario.setSobrenome(dto.sobrenome());
        newUsuario.setCpf(dto.cpf());
        newUsuario.setUsername(dto.username());
        newUsuario.setEmail(dto.email());

        newUsuario.setSenha(hashService.getHashSenha(dto.senha()));

        newUsuario.setPerfil(Perfil.valueOf(dto.perfil().getId()));

        repository.persist(newUsuario);

        return UsuarioResponseDTO.valueOf(newUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioDTO dto) {
        if (repository.findById(id) == null) {
            throw new NotFoundException("Usuario não encontrado");
        }

        if (dto.email() == null || dto.email().isEmpty() || dto.senha() == null || dto.senha().isEmpty()) {
            throw new ValidationException("400", "Email e senha não podem estar em branco");
        }

        if (Perfil.valueOf(dto.perfil().getId()) == null) {
            throw new ValidationException("400", "O tipo de perfil não pode ser nulo");
        }

        Usuario usuario = repository.findById(id);
        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Usuario não encontrado");
        }
    }

    @Override
    public UsuarioResponseDTO findById(long id) {
        if (repository.findById(id) == null) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public UsuarioResponseDTO findByUsername(String username) {
        if (repository.findByUsername(username) == null) {
            throw new NotFoundException("Username não encontrado");
        }
        return UsuarioResponseDTO.valueOf(repository.findByUsername(username));

    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado para o e-mail fornecido");
        }

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByEmailSenha(String email, String senha) {
        Usuario usuario = repository.findByEmailSenha(email, senha);
        if (usuario == null)
            throw new ValidationException("login", "Login ou senha inválido");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        if (repository.listAll().stream().map(e -> UsuarioResponseDTO.valueOf(e)).toList().isEmpty()) {
            throw new NotFoundException("Não há usuarios");
        }
        return repository.listAll().stream().map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<UsuarioResponseDTO> findVeterinario() {
        List<Usuario> veterinarios = repository.listAll().stream().filter(usuario -> usuario.getPerfil().getId() == 3)
                .collect(Collectors.toList());

        return veterinarios.stream().map(usuario -> UsuarioResponseDTO.valueOf(usuario)).toList();
    }

    // ------------------------------------------

    @Override
    @Transactional
    public UsuarioResponseDTO updateNome(String login, NomeDTO nomeDTO) {

        Usuario user = repository.findByEmail(login);
        user.setNome(nomeDTO.nome());
        user.setSobrenome(nomeDTO.sobrenome());

        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateCPF(String login, CpfDTO cpfDTO) {
        if (repository.existsByCpf(cpfDTO.cpf())) {
            throw new ValidationException("cpf", "O cpf já está cadastrado");
        }

        Usuario user = repository.findByEmail(login);
        user.setCpf(cpfDTO.cpf());

        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateUsername(String login, UsernameDTO newUsername) {

        if (repository.existsByUsername(newUsername.username())) {
            throw new ValidationException("400", "O nome de usuario já existe");
        }

        Usuario user = repository.findByEmail(login);

        user.setUsername(newUsername.username());

        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateEmail(String login, EmailDTO newEmail) {

        if (newEmail.email() == null || newEmail.email().isEmpty()) {
            throw new ValidationException("400", "O valor fornecido é invalido");
        }

        if (repository.existsByEmail(newEmail.email())) {
            throw new ValidationException("400", "O email já existe");
        }

        Usuario user = repository.findByEmail(login);

        if (user != null) {
            user.setEmail(newEmail.email().replaceAll("^\"|\"$", ""));
        } else {
        }

        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateSenha(String login, UpdateSenhaDTO updateSenha) {

        if (updateSenha.novaSenha() == null || updateSenha.novaSenha().isEmpty()) {
            throw new ValidationException("400", "O valor fornecido é invalido");
        }

        Usuario user = repository.findByEmail(login);

        if (!hashService.getHashSenha(updateSenha.senhaAtual()).equals(user.getSenha())) {
            throw new ForbiddenException("Acesso negado. A senha informada é incorreta");
        }

        if (user != null) {

            if (hashService.getHashSenha(updateSenha.senhaAtual()).equals(user.getSenha())) {
                user.setSenha(hashService.getHashSenha(updateSenha.novaSenha()));
            }

        }

        return UsuarioResponseDTO.valueOf(user);
    }


    @Override
    @Transactional
    public UsuarioResponseDTO changeImage(Long id, String imageName) {
        Usuario usuario = repository.findById(id);
        if (usuario != null) {
            usuario.setImagem(imageName);
            return UsuarioResponseDTO.valueOf(usuario);
        } else {
            // Trate o caso em que o usuário não foi encontrado
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }

}
