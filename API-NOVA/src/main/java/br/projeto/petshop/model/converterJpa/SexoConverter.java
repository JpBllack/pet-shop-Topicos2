package br.projeto.petshop.model.converterJpa;

import br.projeto.petshop.model.Sexo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Sexo sexo) {
        return (sexo == null ? null : sexo.getId());
    }

    @Override
    public Sexo convertToEntityAttribute(Integer id) {
        return Sexo.valueOf(id);
    }

    
}
