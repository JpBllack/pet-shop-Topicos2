package br.projeto.petshop.service;

import br.projeto.petshop.dto.MunicipioDTO;
import br.projeto.petshop.dto.MunicipioResponseDTO;
import br.projeto.petshop.model.Estado;
import br.projeto.petshop.model.Municipio;
import br.projeto.petshop.repository.EstadoRepository;
import br.projeto.petshop.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    EstadoRepository estadoRepository;

    private static final Logger LOG = Logger.getLogger(MunicipioServiceImpl.class);

    @Override
    public List<MunicipioResponseDTO> getAll() {
        List<Municipio> municipios = municipioRepository.listAll();
        if (municipios.isEmpty()) {
            throw new NotFoundException("Não há municípios disponíveis");
        }
        return municipioRepository.findAll().stream().map(e -> MunicipioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public MunicipioResponseDTO getById(long id) {
        Municipio municipio = municipioRepository.findById(id);
        if (municipio == null) {
            throw new NotFoundException("Município não encontrado");
        }
        return MunicipioResponseDTO.valueOf(municipioRepository.findById(id));
    }

    @Override
    public List<MunicipioResponseDTO> getByEstadoId(Long id){
        return municipioRepository.findByEstadoId(id).stream().map(e -> MunicipioResponseDTO.valueOf(e)).toList();
    }


    @Override
    @Transactional
    public void insertMunicipio(MunicipioDTO municipioDTO) {
    // Cria um novo objeto Municipio
    Municipio municipio = new Municipio();
    municipio.setNome(municipioDTO.nome());

    municipio.setEstado(estadoRepository.findById(municipioDTO.estado()));

    // Persiste o novo município no banco de dados
    municipioRepository.persistMunicipio(municipio);
    
    // Log de sucesso
    LOG.info("Município inserido com sucesso: " + municipio);
}



@Override
@Transactional
public void updateMunicipio(long id, MunicipioDTO municipioDTO) {
    // Encontra o município pelo ID
    Municipio municipio = municipioRepository.findById(id);
    if (municipio == null) {
        LOG.warn("Município não encontrado para o ID: " + id);
        throw new NotFoundException("Município não encontrado");
    }
    
    // Atualiza o nome do município com o novo nome fornecido
    municipio.setNome(municipioDTO.nome());
    
    // Define o Estado no município
    municipio.setEstado(estadoRepository.findById(municipioDTO.estado()));;
    
    // Atualiza o município no banco de dados
    municipioRepository.updateMunicipio(municipio);
    
    // Log de sucesso
    LOG.info("Município atualizado com sucesso: " + municipio);
}


    @Override
    @Transactional
    public void deleteMunicipio(long id) {
        if (!municipioRepository.deleteById(id)) {
            LOG.warn("Município não encontrado para o ID: " + id);
            throw new NotFoundException("Município não encontrado");
        }
        LOG.info("Município deletado com sucesso");
    }
}
