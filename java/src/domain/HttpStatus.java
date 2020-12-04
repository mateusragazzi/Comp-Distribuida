package src.domain;

public enum HttpStatus {
    OK(200, "Document follows"),
    NOT_FOUND(404, "Documento not found"),
    BAD_REQUEST(400, "Bad Request"),
    METHOD_NOT_ALLOWED(405, "Method not allowed");

    private final int statusCode;
    private final String message;

    HttpStatus(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Função que retorna os códigos de uma determinada requisição.
     *
     * @return String código de uma requisição.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Função que retorna a mensagem de uma requisição.
     *
     * @return String status da requisição.
     */
    public String getMessage() {
        return message;
    }
}
