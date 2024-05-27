package br.projeto.petshop.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class StatusCompra extends DefaultEntity{
    Status status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @PrePersist
    protected void onCreate() {
        if (this.data == null) {
            this.data = new Date();
        }
    }

    
}
