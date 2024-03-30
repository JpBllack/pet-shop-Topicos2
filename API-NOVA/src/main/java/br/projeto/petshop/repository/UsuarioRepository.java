package br.projeto.petshop.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import br.projeto.petshop.model.Usuario;

import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario, String> {

    public List<Usuario> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

    public Usuario findByEmailAndSenha(String email, String senha){
        if (email == null || senha == null)
            return null;

        return find("email = ?1 AND senha = ?2 ", email, senha).firstResult();
    }

    public Usuario findByUsernameAndSenha(String username, String senha){
        if (username == null || senha == null)
            return null;

        return find("username = ?1 AND senha = ?2 ", username, senha).firstResult();
    }

    public Usuario findByCpf(String cpf){
        if (cpf == null)
            return null;

        return find("cpf = ?1", cpf).firstResult();
    }

    public Usuario findByEmail(String email){
        if (email == null)
            return null;

        return find("email = ?1", email).firstResult();
    }

    public Usuario findByUsername(String username){
        if (username == null)
            return null;

        return find("username = ?1", username).firstResult();
    }

    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }

    public boolean existsByUsername(String username){
        return find("username", username).count() > 0;
    }

}
