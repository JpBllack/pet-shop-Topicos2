package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.BoletoBancario;

import java.util.List;

@ApplicationScoped
public class BoletoBancarioRepository implements PanacheRepository<BoletoBancario> {

}
