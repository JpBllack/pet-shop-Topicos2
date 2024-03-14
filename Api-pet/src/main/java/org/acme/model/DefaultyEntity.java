package org.acme.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class DefaultyEntity {

    private LocalDateTime dataInclusao;


    @PrePersist // pre inclusao
    private void prePersisting() {
        this.dataInclusao = LocalDateTime.now();
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

}
