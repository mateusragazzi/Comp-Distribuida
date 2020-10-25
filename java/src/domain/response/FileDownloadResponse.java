package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO: download de arquivo binario (e.g pdf)
public class FileDownloadResponse extends Response {
    private final File requestedFile;

    public FileDownloadResponse(File requestedFile) {
        this.requestedFile = requestedFile;
    }

    /**
     * Monta a resposta para o servidor, concatenando o header com o body
     * Utilizada em Http.java, caso a Factory tenha o invocado.
     * @return resposta formada.
     */
    @Override
    public String buildResponse() {
        return makeHeaders() + makeResponseBody();
    }

    /**
     * Monta o header para servidor, colocando:
     *    - Status da requisição, utilizando Enum de HttpStatus;
     *    - tamanho do body de resposta;
     *    - tipo do arquivo (PDF, JPEG, ETC);
     *
     * @return header formado.
     */
    @Override
    protected String makeHeaders() {
        String fileName = requestedFile.getName();

        return makeBaseHeaders(HttpStatus.OK, (int) requestedFile.length()) +
                "Content-Type: application/octet-stream\r\n" +
                String.format("Content-Disposition: attachment; filename=%s", fileName) +
                "\r\n\n";
    }

    /**
     * Monta o body da requisição, que no caso é o conteúdo do arquivo.
     * @return arquivo lido.
     */
    @Override
    protected String makeResponseBody() {
        return processFile(requestedFile) + "\r\n\n";
    }

    /**
     * Lê e processa o arquivo, retornando o conteúdo do arquivo dentro de uma String.
     * @param requestedFile indica o arquivo a ser lido.
     * @return conteúdo do arquivo.
     */
    private String processFile(File requestedFile) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(requestedFile.getAbsolutePath()));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException ie) {
            System.err.println(ie.getMessage());
            return "";
        }
    }
}
