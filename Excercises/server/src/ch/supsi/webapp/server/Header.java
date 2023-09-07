package ch.supsi.webapp.server;

import javax.xml.crypto.Data;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Header {
    private String version;
    private int status;
    private String statusMessage;
    private final static String CONTENT_LENGTH_HEADER = "Content-Length";
    private String contentType;
    private int contentLength;
    private final static String LINEBREAK = "\r\n";

    public Header(String version, int status, String statusMessage, String contentType, int contentLength) {
        this.version = version;
        this.status = status;
        this.statusMessage = statusMessage;
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    @Override
    public String toString() {
        return version+" "+status+" "+statusMessage+LINEBREAK+
                "Date: "+ new Date() +LINEBREAK+
                "Server: JavaServer/1.0"+LINEBREAK+
                "Content-Type: "+contentType+LINEBREAK+
                "Content-Length: "+contentLength+LINEBREAK+LINEBREAK;
    }
}
