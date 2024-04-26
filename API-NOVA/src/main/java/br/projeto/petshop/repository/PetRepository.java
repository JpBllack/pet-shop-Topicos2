package br.projeto.petshop.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Usuario;

@ApplicationScoped
public class PetRepository implements PanacheRepository<Pet> {

    // Método para encontrar um pet pelo nome
    public Pet findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    // Método para atualizar um pet existente
    public void update(Pet pet) {
        Pet petToUpdate = findById(pet.getId());
        if (petToUpdate != null) {
            petToUpdate.setNome(pet.getNome());
            petToUpdate.setAnoNascimento(pet.getAnoNascimento());
            petToUpdate.setTipoAnimal(pet.getTipoAnimal());
            petToUpdate.setSexo(pet.getSexo());
            petToUpdate.setUsuario(pet.getUsuario()); // Atualiza o campo Usuario
            persist(petToUpdate);
        } else {
            throw new IllegalArgumentException("Pet não encontrado com ID: " + pet.getId());
        }
    }

    // Método para buscar todos os pets
    public List<Pet> buscarTodos() {
        return listAll();
    }

    // Método para buscar um pet pelo nome
    public Pet buscarPorNome(String nome) {
        return find("nome", nome).firstResult();
    }

    // Método para buscar um pet pelo ID
    public Pet buscarPorId(Long id) {
        return findById(id);
    }

    // Método para salvar um novo pet
    public void salvar(Pet pet) {
        persist(pet);
    }

    // Método para atualizar um pet (alternativo ao método `update`)
    public void atualizar(Pet pet) {
        update("nome = ?1, anoNascimento = ?2, tipoAnimal = ?3, sexo = ?4, usuario_id = ?5 where id = ?6",
                pet.getNome(), pet.getAnoNascimento(), pet.getTipoAnimal(), pet.getSexo(), pet.getUsuario().getId(), pet.getId());
    }

    // Método para remover um pet
    public void remover(Pet pet) {
        delete(pet);
    }
}
