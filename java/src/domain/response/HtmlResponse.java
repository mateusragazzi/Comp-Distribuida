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

    public String buildResponse() {
        return makeHeaders() + makeResponseBody() + "\r\n";
    }
    protected String makeHeaders() {
        return makeBaseHeaders(HttpStatus.OK) + "Content-Type: text/html \r\n\n";
    }

    @Override
    protected String makeResponseBody() {
        File[] contents = requestedFile.listFiles();
        return listDirectoriesAsHtml(contents);
    }

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

    private String buildAnchorTag(File file) {
        return String.format("<a href='%s'>%s</a>", baseUrl + callStack + file.getName(), file.getName());
    }
}
