package my.orange.reportgen.document;

import org.apache.commons.text.WordUtils;

public class Cell {

    private String[] strings;
    private int width;

    public Cell(String cell, int columnWidth) {
        this.strings = WordUtils.wrap(cell, columnWidth, "\n", true).split("\n");
        width = columnWidth;
        for (int k=0; k < strings.length; k++) {
            if (strings[k].length() < columnWidth) {
                StringBuilder builder = new StringBuilder(strings[k]);
                for (int i = 0; i < columnWidth - strings[k].length(); i++) {
                    builder.append(" ");
                }
                strings[k] = builder.toString();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public String[] getStrings() {
        return strings;
    }
}
