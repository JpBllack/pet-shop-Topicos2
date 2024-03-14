package org.acme.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.dto.*;
import org.acme.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> getAll();

    UsuarioResponseDTO getId(String id);

    Usuario findByLoginAndSenha(String login, String senha);

    List<UsuarioResponseDTO> getNome(String nome);

    UsuarioResponseDTO getCpf(String cpf);

    UsuarioResponseDTO updateEmail(String id, UsuarioUpdateEmailDTO email);

    UsuarioResponseDTO updateNome(String id, UsuarioUpdateNomeDTO nome);

    UsuarioResponseDTO updateLogin(String id, UsuarioUpdateLoginDTO login);

    UsuarioResponseDTO updateSenha(String id, UsuarioUpdateSenhaDTO senha);

    Response promoverAdmin(String id);

    Response insert(UsuarioDTO usuarioDTO);

    Response delete(@PathParam("id") String id);

}
