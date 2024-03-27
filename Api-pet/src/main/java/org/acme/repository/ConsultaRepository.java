package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.model.Consulta;
import org.acme.model.Usuario;


import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsultaRepository implements PanacheRepository<Consulta> {

    public List<Consulta> findByMotivo(String motivo) {
        if (motivo == null || motivo.isEmpty()) {
            return listAll(); // Retorna todas as consultas se o motivo for nulo ou vazio
        }
        return find("motivo", motivo).list();
    }

    public List<Consulta> findByUsuario(Usuario usuario) {
        if (usuario == null) {
            return listAll(); // Retorna todas as consultas se o usuário for nulo
        }
        return find("usuario", usuario).list();
    }
}
