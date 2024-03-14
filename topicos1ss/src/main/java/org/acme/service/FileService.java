package org.acme.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    public String salvarImagemProduto(byte[] imagem, String nomeImagem) throws IOException;

    public File download(String nomeArquivo);
}