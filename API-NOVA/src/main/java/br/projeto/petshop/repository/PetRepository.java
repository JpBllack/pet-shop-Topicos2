package br.projeto.petshop.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

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
            petToUpdate.setAnoNascimento(pet.getAnoNascimento());
            petToUpdate.setTipoAnimal(pet.getTipoAnimal());
            petToUpdate.setSexo(pet.getSexo());
            persist(petToUpdate);
        } else {
            throw new IllegalArgumentException("Animal não encontrado com ID: " + pet.getId());
        }
    }
    

    // Outros métodos de consulta podem ser adicionados conforme necessário
}
