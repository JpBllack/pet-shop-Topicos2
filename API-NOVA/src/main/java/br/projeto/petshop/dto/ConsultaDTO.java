package br.projeto.petshop.dto;

import br.projeto.petshop.model.Consulta;

import java.util.Date;

public class ConsultaDTO {
    private Long id;
    private Date data;
    private String motivo;
    private String veterinarioId;

    public ConsultaDTO() {
       
    }

    public ConsultaDTO(Consulta consulta) {
        this.id = consulta.getId();
        this.data = consulta.getData();
        this.motivo = consulta.getMotivo();
        this.veterinarioId = consulta.getVeterinario().getId(); 
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(String veterinarioId) {
        this.veterinarioId = veterinarioId;
    }
}
