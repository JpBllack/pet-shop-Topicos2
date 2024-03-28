package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.repository.UserRepository;
import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.dto.LoginDTO;
import br.projeto.petshop.dto.LoginResponseDTO;
import br.projeto.petshop.dto.UserDTO;
import br.projeto.petshop.dto.UserResponseDTO;
import br.projeto.petshop.model.Profile;
import br.projeto.petshop.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Override
    @Transactional
    public LoginResponseDTO insertBasicUser(LoginDTO dto) {
        if(dto.email() == null || dto.email().isEmpty()){
            throw new ValidationException("400", "O email não pode estar em branco");
        } else if(dto.password() == null || dto.password().isEmpty()){
            throw new ValidationException("400", "A senha não pode estar em branco");
        }

        if(repository.existsByEmail(dto.email())){
            throw new ValidationException("400", "O email já existe");
        }

        User newUser = new User();

        newUser.setEmail(dto.email());

        newUser.setPassword(hashService.getHashPassword(dto.password()));

        newUser.setProfile(Profile.valueOf(1));

        repository.persist(newUser);

        return LoginResponseDTO.valueOf(newUser);
    }

    @Override
    @Transactional
    public UserResponseDTO insert(UserDTO dto) {
        if(dto.email() == null || dto.email().isEmpty() || dto.password() == null || dto.password().isEmpty()) {
            throw new ValidationException("400", "Email e senha não podem estar em branco");
        }

        if(Profile.valueOf(dto.profile()) == null){
            throw new ValidationException("400", "O tipo de perfil não pode ser nulo");
        }

        User newUser = new User();

        newUser.setUsername(dto.username());
        newUser.setEmail(dto.email());

        newUser.setProfile(Profile.valueOf(dto.profile()));

        newUser.setPassword(hashService.getHashPassword(dto.password()));

        repository.persist(newUser);

        return UserResponseDTO.valueOf(newUser);
    }

    @Override
    @Transactional
    public UserResponseDTO update(Long id, UserDTO dto) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Usuario não encontrado");
        }

        if(dto.email() == null || dto.email().isEmpty() || dto.password() == null || dto.password().isEmpty()) {
            throw new ValidationException("400", "Email e senha não podem estar em branco");
        }

        if(Profile.valueOf(dto.profile()) == null){
            throw new ValidationException("400", "O tipo de perfil não pode ser nulo");
        }

        User user = repository.findById(id);
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(hashService.getHashPassword(dto.password()));

        return UserResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Usuario não encontrado");
        }
    }

    @Override
    public UserResponseDTO findById(long id) {
        if(repository.findById(id) == null) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return UserResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        if(repository.findByUsername(username) == null) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return UserResponseDTO.valueOf(repository.findByUsername(username));

    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        User user = repository.findByEmail(email);
        if(email == null){
            throw new ValidationException("email", "Email invalido");
        }
        return UserResponseDTO.valueOf(user);
    }

    @Override
    public UserResponseDTO findByEmailAndPassword(String email, String password) {
        User user = repository.findByEmailAndPassword(email, password);
        if (user == null) 
            throw new ValidationException("login", "Login ou senha inválido");
        
        return UserResponseDTO.valueOf(user);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        if(repository.listAll().stream().map(e -> UserResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há usuarios");
        }
        return repository.listAll().stream().map(e -> UserResponseDTO.valueOf(e)).toList();
    }
    
}
