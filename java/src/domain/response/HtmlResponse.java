package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class HtmlResponse extends Response{
    private final URL baseUrl;

    public HtmlResponse(URL baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String makeHeaders() {
        return makeBaseHeaders(HttpStatus.OK) + "\r\nContent-Type: text/html \r\n\n";
    }

    @Override
    public String makeResponse(File requestedFile) {
        File[] contents = requestedFile.listFiles();
        return listDirectoriesAsHtml(contents);
    }

    private String listDirectoriesAsHtml(File[] contents) {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body>");
        html.append("<ul>");
        Arrays.stream(contents)
                .forEach(file -> html.append("<li>")
                        .append(buildAnchorTag(file))
                        .append("</li>"));
        html.append("</ul>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    private String buildAnchorTag(File file) {
        return String.format("<a href='%s'>%s</a>", baseUrl + file.getName(), file.getName());
    }
}
