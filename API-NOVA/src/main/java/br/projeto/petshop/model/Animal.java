package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Animal {
    CACHORRO(1, "Cachorro"),
    GATO(2, "Gato"),
    PEIXE(3, "Peixe"),
    PASSARO(4, "Pássaro");

    private final Integer id;
    private final String label;

    private Animal(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Animal valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Animal animal : Animal.values()) {
            if (animal.getId().equals(id))
                return animal;
        }

        throw new IllegalArgumentException("ID inválido para Animal: " + id);
    }
}
