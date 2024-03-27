package org.acme.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Veterinario extends DefaultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

   

    
    @NotBlank(message = "O campo 'nome' não pode estar em branco")
    @Size(max = 100, message = "O campo 'nome' deve ter no máximo 100 caracteres")
    @Size(min = 3, message = "O campo 'nome' deve ter no minimo 3 caracteres")
    private String nome;

    @Email
    private String email;

    @CPF
    private String cpf;

    @ElementCollection
    @CollectionTable(name = "veterianrio_perfil", joinColumns = @JoinColumn(name = "id_veterianrio", referencedColumnName = "id"))
    @Column(name = "perfil", length = 30)
    private Set<Perfil> perfis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Perfil> getPerfis() {
        if( perfis == null){
            perfis =  new HashSet<>();
        }
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
}

