package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.LoginDTO;
import br.projeto.petshop.dto.LoginResponseDTO;
import br.projeto.petshop.dto.UsuarioDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;

public interface UsuarioService {

    public LoginResponseDTO inserirUsuarioBasico(LoginDTO dto);

    public UsuarioResponseDTO insert(UsuarioDTO dto);

    public UsuarioResponseDTO update(Long id, UsuarioDTO dto);

    public void delete(long id);

    public UsuarioResponseDTO findById(long id);

    public UsuarioResponseDTO findByUsername(String username);

    public UsuarioResponseDTO findByEmail(String email);

    public UsuarioResponseDTO findByEmailSenha(String email, String password);

    public List<UsuarioResponseDTO> findAll();

    public List<UsuarioResponseDTO> findVeterinario();
}
