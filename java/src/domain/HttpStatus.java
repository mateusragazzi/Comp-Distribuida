package src.domain;

public enum HttpStatus {
    OK("200", "Document follows"),
    NOT_FOUND("404", "Document Not Found"),
    BAD_REQUEST("400", "Bad Request");

    private final String statusCode;
    private final String message;

    HttpStatus(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Função que retorna os códigos de uma determinada requisição.
     * @return String código de uma requisição.
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Função que retorna a mensagem de uma requisição.
     * @return String status da requisição.
     */
    public String getMessage() {
        return message;
    }
}
