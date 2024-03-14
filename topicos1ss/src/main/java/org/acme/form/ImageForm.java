package org.acme.form;


import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;

public class ImageForm{

    @FormParam("nomeImagem")
    private String nome;

    @FormParam("imagem")
    @PartType("application/octet-stream")
    private byte[] imagem;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}