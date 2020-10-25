package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class HtmlResponse extends Response{
    private final File requestedFile;
    private final URL baseUrl;
    private final String callStack;

    public HtmlResponse(File requestedFile, URL baseUrl, String callStack) {
        this.requestedFile = requestedFile;
        this.baseUrl = baseUrl;
        this.callStack = callStack;
    }

    /**
     * Monta a resposta para o servidor, concatenando o header com o body
     * Utilizada em Http.java, caso a Factory tenha o invocado.
     * @return resposta formada.
     */
    public String buildResponse() {
        return makeHeaders() + makeResponseBody() + "\r\n";
    }

    /**
     * Monta o header para servidor, colocando:
     *    - Status da requisição, utilizando Enum de HttpStatus;
     *    - tamanho do body de resposta;
     *    - tipo da resposta, que no caso é HTML;
     *
     * @return header formado.
     */
    protected String makeHeaders() {
        return makeBaseHeaders(HttpStatus.OK, makeResponseBody().length()) + "Content-Type: text/html \r\n\n";
    }

    /**
     * Monta o body da requisição, que no caso é a lista de diretórios.
     * @return lista de diretórios encontrados.
     */
    @Override
    protected String makeResponseBody() {
        File[] contents = requestedFile.listFiles();
        return listDirectoriesAsHtml(contents);
    }

    /**
     * Função que lê os itens de um diretório e forma o conteúdo do HTML
     * @param directories lista de arquivos e diretórios encontrados.
     * @return String com todos os itens formatados.
     */
    private String listDirectoriesAsHtml(File[] directories) {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body>");
        html.append("<ul>");
        Arrays.stream(directories)
                .forEach(file -> html.append("<li>")
                        .append(buildAnchorTag(file))
                        .append("</li>"));
        html.append("</ul>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    /**
     * Função que monta o link a ser exibido na tela.
     * @param file arquivo a ser aberto, caso o cliente clique.
     * @return retorna a tag anchor (<a></a>) pronta para uso.
     */
    private String buildAnchorTag(File file) {
        return String.format("<a href='%s'>%s</a>", baseUrl + callStack + file.getName(), file.getName());
    }
}
