package br.projeto.petshop.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import br.projeto.petshop.model.Pet;

@ApplicationScoped
public class PetRepository implements PanacheRepository<Pet> {

    public Pet findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public void update(Pet pet) {
        Pet petToUpdate = findById(pet.getId());
        if (petToUpdate != null) {
            petToUpdate.setNome(pet.getNome());
            petToUpdate.setUsuario(pet.getUsuario());
            petToUpdate.setAnoNascimento(pet.getAnoNascimento());
            petToUpdate.setTipoAnimal(pet.getTipoAnimal());
            petToUpdate.setSexo(pet.getSexo());
            persist(petToUpdate);
        } else {
            throw new IllegalArgumentException("Animal não encontrado com ID: " + pet.getId());
        }
    }

    public List<Pet> buscarTodos() {
        return listAll(); // Retorna todos os pets do banco de dados
    }

    public Pet buscarPorNome(String nome) {
        return find("nome", nome).firstResult(); // Busca um pet pelo nome
    }

    public Pet buscarPorId(Long id) {
        return findById(id); // Busca um pet pelo ID
    }

    public void salvar(Pet pet) {
        persist(pet); // Salva um novo pet no banco de dados
    }

    public void atualizar(Pet pet) {
        update("nome = ?1, anoNascimento = ?2, tipoAnimal = ?3, sexo = ?4 where id = ?5",
                pet.getNome(), pet.getAnoNascimento(), pet.getTipoAnimal(), pet.getSexo(), pet.getId());
        // Atualiza os dados de um pet no banco de dados
    }

    public void remover(Pet pet) {
        delete(pet); // Remove um pet do banco de dados
    }
}

