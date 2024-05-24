package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Idade {
    FILHOTE(1, "Filhote"),
    ADULTO(2, "Adulto"),
    IDOSO(3, "Idoso"),
    NAO_DEFINIFO(4, "Não Definido");

    private final Integer id;
    private final String label;

    private Idade(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Idade valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Idade idade : Idade.values()) {
            if (idade.getId().equals(id))
                return idade;
        }

        throw new IllegalArgumentException("ID inválido para Idade: " + id);
    }

}
