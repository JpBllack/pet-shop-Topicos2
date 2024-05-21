package br.projeto.petshop.model.converterJpa;

import br.projeto.petshop.model.Peso;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PesoConverter implements AttributeConverter<Peso, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Peso peso) {
        return (peso == null ? null : peso.getId());
    }

    @Override
    public Peso convertToEntityAttribute(Integer id) {
        return Peso.valueOf(id);
    }
    
}
