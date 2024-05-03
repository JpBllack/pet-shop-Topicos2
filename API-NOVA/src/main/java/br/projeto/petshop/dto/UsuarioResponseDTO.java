package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Usuario;

import java.util.logging.Level;
import java.util.logging.Logger;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String cpf,
    String username,
    String email,
    Perfil perfil
) {
    // Criando um objeto Logger para a classe UsuarioResponseDTO
    private static final Logger logger = Logger.getLogger(UsuarioResponseDTO.class.getName());

    // Método para converter um objeto Usuario em UsuarioResponseDTO
    public static UsuarioResponseDTO valueOf(Usuario user) {
        // Verificando se o objeto Usuario é null
        if (user == null) {
            // Registrando uma mensagem de erro caso o objeto Usuario seja null
            logger.log(Level.SEVERE, "O objeto Usuario passado para valueOf é null");
            // Você pode escolher retornar null ou uma instância vazia de UsuarioResponseDTO
            // Aqui retornamos null, mas você pode ajustar conforme necessário.
            return null;
        }
        
        // Retornando uma nova instância de UsuarioResponseDTO com base nos atributos de Usuario
        return new UsuarioResponseDTO(
            user.getId(),
            user.getNome(),
            user.getCpf(),
            user.getUsername(),
            user.getEmail(),
            user.getPerfil()
        );
    }
}
