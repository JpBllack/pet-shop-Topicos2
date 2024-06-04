package br.projeto.petshop.service;

import java.io.File;
import java.io.IOException;

public interface FileServiceUsuario {
    String save(Long userId,String fileName, byte[] file) throws IOException;

    File getFile(String fileName); 
}
