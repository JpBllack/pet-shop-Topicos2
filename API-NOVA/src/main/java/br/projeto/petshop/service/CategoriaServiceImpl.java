

package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import br.projeto.petshop.dto.CategoriaDTO;
import br.projeto.petshop.dto.CategoriaResponseDTO;
import br.projeto.petshop.model.Categoria;
import br.projeto.petshop.repository.CategoriaRepository;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {
    public static final Logger LOG = Logger.getLogger(CategoriaServiceImpl.class);
    @Inject
    CategoriaRepository repository;

    @Override
    public List<CategoriaResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream()
                    .map(CategoriaResponseDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public CategoriaResponseDTO getId(long id) {

        try {
            LOG.info("Requisição getId()");
            return new CategoriaResponseDTO(repository.findById(id));
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    public List<CategoriaResponseDTO> getNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(CategoriaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Response insert(CategoriaDTO dto) {

        try {
            LOG.info("Requisição insert()");

            Categoria categoria = new Categoria();
            categoria.setNome(dto.nome());
            repository.persist(categoria);
            return Response.ok(new CategoriaResponseDTO(categoria)).build();

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.noContent().build();
        }
    }

    @Transactional
    @Override
    public Response delete(long id) {

        try {
            LOG.info("Requisição delete()");
            Categoria categoria = repository.findById(id);
            if (categoria != null) {
                repository.delete(categoria);
                return Response.ok().build();
            } else {
                throw new Exception("não encontrado!");
            }
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição delete()");
            return Response.notModified(e.getMessage()).build();
        }
    }
}
