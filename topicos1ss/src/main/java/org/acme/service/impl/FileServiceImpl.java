package org.acme.service.impl;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.form.ImageForm;
import org.acme.service.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.jboss.logging.Logger;


@ApplicationScoped
public class FileServiceImpl implements FileService {

    public static final Logger LOG = Logger.getLogger(FileServiceImpl.class);

    private final String PATH_PRODUTO = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "produto" + File.separator;

    @Override
    public String salvarImagemProduto(byte[] imagem, String nomeImagem) throws IOException{


        try {

            //verificando tipo da imagem
            String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
            List<String> listMimeType = Arrays.asList("image/jpg", "image/png", "image/gif", "image/img");

            if(!listMimeType.contains(mimeType)) {
                throw new IOException("Tipo de imagem não suportada");
            }

            //verificando tamanho da imagem
            if(imagem.length > (1024 * 1024 * 10)){
                throw new IOException("Arquivo é muito grande");
            }

            //criando as pastas quando não existir
            File diretorio = new File(PATH_PRODUTO);
            if(!diretorio.exists())
                diretorio.mkdirs();

            //
            String nomeArquivo = UUID.randomUUID()
                    + "." + mimeType.substring(mimeType.lastIndexOf("/")+ 1);

            String path = PATH_PRODUTO + nomeArquivo;

            File file = new File (path);
            if(file.exists())
                throw new IOException("O nome gerado da imagem está repetido");

            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(imagem);

            fos.flush();
            fos.close();
            ImageForm i = new ImageForm();
            i.setNome(nomeImagem);
            i.setImagem(imagem);

            LOG.info("Requisição Fil.salvarImagemUsuario()");
            return nomeArquivo;


        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Estado.getAll()");
            return null;
        }
    }

    @Override
    public File download(String nomeArquivo) {

        try {
            LOG.info("Requisição File.download()");

            File file = new File(PATH_PRODUTO+nomeArquivo);
            return file;

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição File.download()");
            return null;
        }

    }

}