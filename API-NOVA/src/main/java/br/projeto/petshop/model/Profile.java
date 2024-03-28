package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Profile {

    USER(1, "User"),
    ADMIN(2, "Admin");

    private final Integer id;
    private final String label;

    Profile(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Profile valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Profile profile : Profile.values()) {
            if (profile.getId().equals(id))
                return profile;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
    
}