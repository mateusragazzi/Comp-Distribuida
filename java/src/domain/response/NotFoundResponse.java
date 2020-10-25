package src.domain.response;

import src.domain.HttpStatus;

public class NotFoundResponse extends Response {

    /**
     * Monta a resposta para o servidor, concatenando o header com o body
     * Utilizada em Http.java, caso a Factory tenha o invocado.
     * @return resposta formada.
     */
    @Override
    public String buildResponse() {
        return makeHeaders();
    }

    /**
     * Monta o header para servidor, colocando:
     *    - Status da requisição,no caso 404;
     *    - tamanho do body de resposta;
     *
     * @return String header formado.
     */
    @Override
    protected String makeHeaders() {
        return makeBaseHeaders(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getMessage().length());
    }

    /**
     * Monta o body da requisição.
     * Neste caso, como nada foi encontrado, retorna uma String vazia.
     * @return retorna uma String vazia.
     */
    @Override
    protected String makeResponseBody() {
        return "";
    }

}
