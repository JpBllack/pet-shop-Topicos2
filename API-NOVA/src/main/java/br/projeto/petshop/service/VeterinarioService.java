/* package br.projeto.petshop.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import br.projeto.petshop.dto.VeterinarioDTO;
import br.projeto.petshop.dto.VeterinarioResponseDTO;
import br.projeto.petshop.model.Veterinario;

import java.util.List;

public interface VeterinarioService {

    List<VeterinarioResponseDTO> getAll();

    VeterinarioResponseDTO getId(String id);

    Veterinario findByLoginAndSenha(String login, String senha);

    List<VeterinarioResponseDTO> getNome(String nome);

    VeterinarioResponseDTO getCpf(String cpf);

    Response insert(VeterinarioDTO veterinarioDTO);

    Response delete(@PathParam("id") String id);

}
 */