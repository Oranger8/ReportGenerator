package my.orange.reportgen;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.File;
import java.util.List;

class Source {

    private List<String[]> data;

    public List<String[]> getData() {
        return data;
    }

    Source(String source) {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        TsvParser parser = new TsvParser(settings);
        data = parser.parseAll(new File(source), "UTF-16");
    }
}
