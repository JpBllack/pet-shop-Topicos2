package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {

    ADMIN(1, "Admin"),
    USER(2, "User"),
    VET(3, "Veterinario");

    private final Integer id;
    private final String label;

    private Perfil(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Perfil valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Perfil perfil : Perfil.values()) {
            if (perfil.getId().equals(id))
                return perfil;
        }

        throw new IllegalArgumentException("id invalido " + id);
    }
}
