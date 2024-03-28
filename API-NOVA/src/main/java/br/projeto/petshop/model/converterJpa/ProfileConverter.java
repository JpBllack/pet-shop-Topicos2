package br.projeto.petshop.model.converterJpa;

import br.projeto.petshop.model.Profile;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProfileConverter implements AttributeConverter<Profile, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Profile profile) {
        return (profile == null ? null : profile.getId());
    }

    @Override
    public Profile convertToEntityAttribute(Integer id) {
        return Profile.valueOf(id);
    }
}

