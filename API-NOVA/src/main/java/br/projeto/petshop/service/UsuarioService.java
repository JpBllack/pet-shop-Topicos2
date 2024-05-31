package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.CpfDTO;
import br.projeto.petshop.dto.EmailDTO;
import br.projeto.petshop.dto.LoginDTO;
import br.projeto.petshop.dto.LoginResponseDTO;
import br.projeto.petshop.dto.NomeDTO;
import br.projeto.petshop.dto.UpdateSenhaDTO;
import br.projeto.petshop.dto.UsernameDTO;
import br.projeto.petshop.dto.UsuarioBasicoDTO;
import br.projeto.petshop.dto.UsuarioBasicoResponseDTO;
import br.projeto.petshop.dto.UsuarioDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;

public interface UsuarioService {

    public UsuarioBasicoResponseDTO inserirUsuarioBasico(UsuarioBasicoDTO dto);

    public UsuarioResponseDTO insert(UsuarioDTO dto);

    public UsuarioResponseDTO update(Long id, UsuarioDTO dto);

    public void delete(long id);

    public UsuarioResponseDTO findById(long id);

    public UsuarioResponseDTO findByUsername(String username);

    public UsuarioResponseDTO findByEmail(String email);

    public UsuarioResponseDTO findByEmailSenha(String email, String password);

    public List<UsuarioResponseDTO> findAll();

    public List<UsuarioResponseDTO> findVeterinario();

    public UsuarioResponseDTO updateUsername(String login, UsernameDTO newUsername);

    public UsuarioResponseDTO updateEmail(String login, EmailDTO newEmail);

    public UsuarioResponseDTO updateSenha(String login, UpdateSenhaDTO updateSenha);

    public UsuarioResponseDTO updateCPF(String login, CpfDTO cpf);

    public UsuarioResponseDTO updateNome(String login, NomeDTO nomeDto);

    public UsuarioResponseDTO changeImage(Long id, String imageName);
    
}
