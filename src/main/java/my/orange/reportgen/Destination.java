package my.orange.reportgen;

import java.io.IOException;
import java.io.PrintWriter;

public class Destination {

    private PrintWriter writer;

    Destination(String target) throws IOException {
        writer = new PrintWriter(target, "UTF-16");
    }

    public void write(String string) {
        writer.write(string);
    }

    public void close() {
        if (writer != null) writer.close();
    }
}
