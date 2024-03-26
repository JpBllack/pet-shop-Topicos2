package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Veterinario;

import java.util.List;

@ApplicationScoped
public class VeterinarioRepository implements PanacheRepositoryBase<Veterinario, String> {

    public List<Veterinario> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }


    public Veterinario findByCpf(String cpf){
        if (cpf == null)
            return null;

        return find("cpf = ?1", cpf).firstResult();
    }

    public Veterinario findByEmail(String email){
        if (email == null)
            return null;

        return find("email = ?1", email).firstResult();
    }


}
