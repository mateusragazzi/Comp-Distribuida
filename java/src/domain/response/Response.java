package src.domain.response;

import src.domain.HttpStatus;

public abstract class Response {
    /**
     * Funções a serem sobrescritas dentro das classes que estendem Response.
     */
    protected abstract String makeResponseBody();

    protected abstract String makeHeaders();

    public abstract String buildResponse();

    /**
     * Função invocada pelos métodos makeHeaders.
     * Devolve o header formatado com status e o tamanho do arquivo.
     *
     * @param httpStatus indica o status HTTP.
     * @param contentLength indica o tamanho do body a ser devolvido.
     * @return header formatado e preenchido.
     */
    protected String makeBaseHeaders(HttpStatus httpStatus, int contentLength) {
        return String.format("HTTP/1.1 " + httpStatus.getStatusCode() + " " + httpStatus.getMessage() + " " + "\r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n" +
                "Content-Length: %d\r\n", contentLength);
    }
}
