package br.projeto.petshop.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String save(String fileName, byte[] file) throws IOException;

    File getFile(String fileName); 
    
}