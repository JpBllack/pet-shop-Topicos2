package br.projeto.petshop.model;

    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
    import jakarta.persistence.InheritanceType;


    @Entity
    @Inheritance(strategy = InheritanceType.JOINED)
    public class Racao {

    @Id
    private Long id;

    
    private String sabor;

    @ManyToOne
    private TipoAnimal animal;

    private Peso peso;
    private Idade idade;

    // Getters e Setters para sabor, animal, peso e idade

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public Peso getPeso() {
        return peso;
    }

    public void setPeso(Peso peso) {
        this.peso = peso;
    }

    public Idade getIdade() {
        return idade;
    }

    public void setIdade(Idade idade) {
        this.idade = idade;
    }

    public TipoAnimal getAnimal() {
        return animal;
    }

    public void setAnimal(TipoAnimal animal) {
        this.animal = animal;
    }
}
