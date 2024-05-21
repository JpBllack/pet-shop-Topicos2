package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Peso {
    G500(1, "500g"),
    KG1(2, "1kg"),
    KG2(3, "2kg"),
    KG5(4, "5kg"),
    KG10(5, "10kg"),
    NDEFINIFO(6, "Não Definido");

    private final Integer id;
    private final String label;

    Peso(Integer id, String label) {
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
        if (id == null) {
            return null;
        }
        for (Peso peso : Peso.values()) {
            if (peso.getId().equals(id)) {
                return peso;
            }
        }
        throw new IllegalArgumentException("ID inválido para Peso: " + id);
    }

    // Método para retornar a label do enum a partir do ID
    public static String getLabelById(Integer id) {
        Peso peso = valueOf(id);
        if (peso != null) {
            return peso.getLabel();
        }
        return null;
    }
}
