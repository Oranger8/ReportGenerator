package my.orange.reportgen;

import my.orange.reportgen.document.Cell;
import my.orange.reportgen.document.Page;
import my.orange.reportgen.document.Row;

import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Expected: java -jar ReportGenerator.jar [settings file].xml [source].tsv [output].txt");
            System.exit(1);
        }
        Settings settings = null;
        try {
            settings = new Settings(args[0]);
        } catch (Exception e) {
            System.out.println("Error while parsing " + args[0]);
            System.exit(1);
        }
        Source source = new Source(args[1]);
        Destination destination = null;
        try {
            destination = new Destination(args[2]);
        } catch (IOException e) {
            System.out.println("Can't write to output file");
            System.exit(1);
        }

        // Making Cells out of source data
        List<String[]> strings = source.getData();
        Cell[][] cells = new Cell[strings.size()][];
        for (int i = 0; i < strings.size(); i++) {
            cells[i] = new Cell[strings.get(i).length];
            for (int j = 0; j < strings.get(i).length; j++) {
                cells[i][j] = new Cell(strings.get(i)[j], settings.getColumns().get(j).getWidth());
            }
        }

        // Making Rows out of Cells
        Row[] rows = new Row[cells.length];
        for (int i = 0; i < cells.length; i++) {
            rows[i] = new Row(cells[i]);
        }

        // Making header Row
        Cell[] hCells = new Cell[settings.getColumns().size()];
        for (int i = 0; i < hCells.length; i++) {
            Settings.Column column = settings.getColumns().get(i);
            hCells[i] = new Cell(column.getTitle(), column.getWidth());
        }
        Row header = new Row(hCells);

        // Make and output pages
        int current = 0;
        StringBuilder builder = new StringBuilder();
        while (current < rows.length) {
            Page page = new Page(header, rows, settings.getPageWidth(), settings.getPageHeight(), current);
            builder.append(page.getPage()).append(System.lineSeparator()).append("~").append(System.lineSeparator());
            current = page.getCurrent();
        }
        destination.write(builder.substring(0, builder.length() - System.lineSeparator().length() * 2 - 1));
        destination.close();
    }
}
