package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Peso {
    _500G(1, "500g"),
    _1KG(2, "1kg"),
    _2KG(3, "2kg"),
    _5KG(4, "5kg"),
    _10KG(5, "10kg");

    private final Integer id;
    private final String label;

    private Peso(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Peso valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Peso peso : Peso.values()) {
            if (peso.getId().equals(id))
                return peso;
        }

        throw new IllegalArgumentException("ID inválido para Peso: " + id);
    }
}
