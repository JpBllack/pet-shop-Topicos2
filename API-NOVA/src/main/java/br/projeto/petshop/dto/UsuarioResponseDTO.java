package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Usuario;

import java.util.logging.Level;
import java.util.logging.Logger;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String username,
        String email,
        Perfil perfil) {

    // Criando um objeto Logger para a classe UsuarioResponseDTO
    private static final Logger logger = Logger.getLogger(UsuarioResponseDTO.class.getName());

    // Método para converter um objeto Usuario em UsuarioResponseDTO
    public static UsuarioResponseDTO valueOf(Usuario user) {

        if (user == null) {
            // Você pode lançar uma exceção específica ou retornar um valor padrão aqui
            throw new IllegalArgumentException("O objeto TipoAnimal não pode ser nulo");
        }
        // Verificando se o objeto Usuario é null

        // Retornando uma nova instância de UsuarioResponseDTO com base nos atributos de
        // Usuario
        return new UsuarioResponseDTO(
                user.getId(),
                user.getNome(),
                user.getSobrenome(),
                user.getCpf(),
                user.getUsername(),
                user.getEmail(),
                user.getPerfil());
    }
}
