package br.projeto.petshop.model.converterJpa;

import br.projeto.petshop.model.Idade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IdadeConverter implements AttributeConverter<Idade, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Idade idade) {
        return (idade == null ? null : idade.getId());
    }

    @Override
    public Idade convertToEntityAttribute(Integer id) {
        return Idade.valueOf(id);
    }
}
