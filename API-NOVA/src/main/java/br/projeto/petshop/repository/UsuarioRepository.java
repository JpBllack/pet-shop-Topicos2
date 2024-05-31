package br.projeto.petshop.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import br.projeto.petshop.model.CartaoCredito;
import br.projeto.petshop.model.Endereco;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.Usuario;

import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    @Inject
    CartaoCreditoRepository cartaoRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Usuario> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

    public Usuario findByEmailSenha(String email, String senha){
        if (email == null || senha == null)
            return null;

        return find("email = ?1 AND senha = ?2 ", email, senha).firstResult();
    }

    public Usuario findByUsernameSenha(String username, String senha){
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

    public Usuario findById(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager.find(Usuario.class, id);
    }
    //Cartao de credito

    public List<CartaoCredito> findCartoesByUsuario(Long idUsuario) {
        if (idUsuario == null) {
            return null;
        }
        return cartaoRepository.find("usuario.id", idUsuario).list();
    }

    //Endereço
    public List<Endereco> findEnderecosByUsuario(Long idUsuario) {
        if (idUsuario == null) {
            return null;
        }
        return enderecoRepository.find("usuario.id", idUsuario).list();
    }


    


    //Verifica se já existe no sistema
    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }

    public boolean existsByUsername(String username){
        return find("username", username).count() > 0;
    }

    public boolean existsByCpf(String cpf){
        return find("cpf", cpf).count() > 0;
    }


    


}
