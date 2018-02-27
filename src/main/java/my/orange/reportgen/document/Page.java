package my.orange.reportgen.document;

public class Page {

    private String page;
    private int current;

    public Page(Row header, Row[] rows, int pageWidth, int pageHeight, int start) {
        StringBuilder builder = new StringBuilder(header.getRow());
        int height = header.getHeight();
        current = start;
        while (current < rows.length && rows[current].getHeight() + height + 1 < pageHeight) {
            builder.append(System.lineSeparator());
            for (int j = 0; j < pageWidth; j++) {
                builder.append("-");
            }
            builder.append(System.lineSeparator()).append(rows[current].getRow());
            height += rows[current].getHeight() + 1;
            current++;
        }
        page = builder.toString();
    }

    public String getPage() {
        return page;
    }

    public int getCurrent() {
        return current;
    }
}
