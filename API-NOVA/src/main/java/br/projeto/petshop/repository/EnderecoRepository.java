package br.projeto.petshop.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import br.projeto.petshop.model.Cidade;
import br.projeto.petshop.model.Endereco;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByUsuario(String id) {
        return find("UPPER(usuario_endereco) LIKE UPPER(?1) ", "%"+id+"%").list();
    }
}
