package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino");

    private final Integer id;
    private final String label;

    private Sexo(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Sexo valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Sexo sexo : Sexo.values()) {
            if (sexo.getId().equals(id))
                return sexo;
        }

        throw new IllegalArgumentException("ID inválido para Sexo: " + id);
    }
}
