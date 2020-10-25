package src.domain.response;

import src.domain.HttpStatus;

import java.io.*;

public class CgiBin extends Response {
    public CgiBin(File requestedFile) {
        super(requestedFile);
    }

    @Override
    protected String makeResponseBody() {
        return "";
    }

    @Override
    protected String makeHeaders() {
        return "";
    }

    @Override
    public String buildResponse() {
        return makeBaseHeaders(HttpStatus.OK) + readProcessOutput();
    }

    private String readProcessOutput() {
        StringBuilder procOutput = new StringBuilder();
        String command = System.getProperty("user.dir") + "/java/resources/datetime.sh";
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            Process proc = pb.start();
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String lineRead;
            while ((lineRead = br.readLine()) != null)
                procOutput
                        .append(lineRead)
                        .append("\r\n");
            proc.destroy();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return procOutput.toString();
    }
}
