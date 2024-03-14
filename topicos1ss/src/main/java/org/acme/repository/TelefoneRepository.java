package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Produto;
import org.acme.model.Telefone;

import java.util.List;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone> {
    public List<Telefone> findByCodigoArea(String codigoArea){
        if (codigoArea == null)
            return null;
        return find("UPPER(CodigoArea) LIKE ?1 ", "%"+codigoArea.toUpperCase()+"%").list();
    }

    public Telefone findByUsuario(String usuario){
        if (usuario == null)
            return null;
        return find("UPPER(telefone_usuario) LIKE ?1 ", "%"+usuario.toUpperCase()+"%").firstResult();
    }
}
