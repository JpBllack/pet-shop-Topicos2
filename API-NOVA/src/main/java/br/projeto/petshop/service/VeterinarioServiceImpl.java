/* package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.projeto.petshop.dto.VeterinarioDTO;
import br.projeto.petshop.dto.VeterinarioResponseDTO;
import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Veterinario;
import br.projeto.petshop.repository.VeterinarioRepository;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VeterinarioServiceImpl implements VeterinarioService {

    public static final Logger LOG = Logger.getLogger(VeterinarioServiceImpl.class);

    @Inject
    VeterinarioRepository repository;

    @Inject
    HashService hash;

    @Override
    public List<VeterinarioResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");
            return repository.listAll().stream()
                    .map(VeterinarioResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public VeterinarioResponseDTO getId(String id) {
        try {
            LOG.info("Requisição getId()");
            return new VeterinarioResponseDTO(repository.findById(id));
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    public List<VeterinarioResponseDTO> getNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(VeterinarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public VeterinarioResponseDTO getCpf(String cpf) {
        return new VeterinarioResponseDTO(repository.findByCpf(cpf));
    }

    @Transactional
    @Override
    public Response insert(VeterinarioDTO dto) {
        try {
            LOG.info("Requisição insert()");
            Veterinario veterinario = new Veterinario();
            veterinario.setNome(dto.nome());
            veterinario.setCpf(dto.cpf());
            veterinario.setEmail(dto.email());
            veterinario.setPerfil(Perfil.VET); // Define o perfil do veterinário

            // Verifica se já existe um veterinário com o mesmo e-mail
            Veterinario existingVeterinario = repository.findByEmail(dto.email());
            if (existingVeterinario != null) {
                throw new Exception("Veterinário com este e-mail já existe");
            }

            repository.persist(veterinario);
            return Response.ok(new VeterinarioResponseDTO(veterinario)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @Override
    public Response delete(String id) {
        try {
            LOG.info("Requisição delete()");
            repository.deleteById(id);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição delete() - " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Veterinario findByLoginAndSenha(String login, String senha) {
        // Não implementado neste método
        throw new UnsupportedOperationException("Unimplemented method 'findByLoginAndSenha'");
    }
}
 */