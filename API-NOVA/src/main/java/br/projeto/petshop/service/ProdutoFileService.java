package br.projeto.petshop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoFileService implements FileService {

    private final String PATH_USER = System.getProperty("user.home") +
        File.separator + "quarkus" +
        File.separator + "images" +
        File.separator + "produto" +  File.separator;
    
    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10mb 

    @Override
    public String save(String fileName, byte[] file) throws IOException {
        
        verifyImageSize(file);
        verifyImageType(fileName);

        Path directory = createDirectoryIfNotExists(PATH_USER);
        String newFileName = generateUniqueFileName(fileName);

        Path filePath = directory.resolve(newFileName);
        saveFile(file, filePath);

        return filePath.toFile().getName();
    }

    private void saveFile(byte[] file, Path filePath) throws IOException {
        if (Files.exists(filePath)) {
    
        }

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(file);
        }
    }

    private Path createDirectoryIfNotExists(String path) throws IOException {
        Path directory = Paths.get(path);
        Files.createDirectories(directory);
        return directory;
    }

    private String generateUniqueFileName(String fileName) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(fileName));
        String extension = mimeType.substring(mimeType.lastIndexOf('/') + 1);

        int counter = 1;
        String uniqueFileName = UUID.randomUUID() + "." + extension;

        while (Files.exists(Paths.get(PATH_USER, uniqueFileName))) {
            uniqueFileName = UUID.randomUUID() + "_" + counter++ + "." + extension;
        }
        
        return uniqueFileName;
    }

    @Override
    public File getFile(String fileName) {
        File file = new File(PATH_USER+fileName);
        return file;
    }

    private void verifyImageSize(byte[] file) throws IOException {
        if (file.length > MAX_FILE_SIZE) 
            throw new IOException("File maior que 10mb.");
    }

    
    private void verifyImageType(String fileName) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(fileName));
        if (mimeType == null || !SUPPORTED_MIME_TYPES.contains(mimeType)) 
            throw new IOException("Tipo de imagem não suportada.");
    }    
    
}
