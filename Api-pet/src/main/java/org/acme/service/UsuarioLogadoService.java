package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.*;

import java.util.List;

public interface UsuarioLogadoService {
    public Response updateSenha(UsuarioLogadoMudarSenhaDTO senha);

    public UsuarioResponseDTO updateLogin(UsuarioUpdateLoginDTO login);
    
    public Response getEndereco();

    public Response updateNome(UsuarioUpdateNomeDTO nome);

    public Response updateEmail(UsuarioUpdateEmailDTO email);
    public Response updateTelefone(TelefoneDTO telefoneDTO);

    public Response updateEndereco(Long id, EnderecoDTO enderecoDTO);

    public UsuarioResponseDTO getPerfilUsuarioLogado();

    public Response insertTelefone(TelefoneDTO telefoneDTO);
    public Response insertEndereco(EnderecoDTO enderecoDTO);

    public Response getVendas();

    public Response deleteOn();
}
