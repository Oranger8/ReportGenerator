package my.orange.reportgen.document;

public class Row {

    private String row;
    private int height;

    public Row(Cell[] cells) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < cells.length; i++)
            height = Math.max(height, cells[i].getStrings().length);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (j == 0) builder.append("| ");
                try {
                    builder.append(cells[j].getStrings()[i]);
                } catch (IndexOutOfBoundsException e) {
                    for (int k = 0; k < cells[j].getWidth(); k++) {
                        builder.append(" ");
                    }
                }
                if (j == cells.length - 1)
                    builder.append(" |").append(System.lineSeparator());
                else
                    builder.append(" | ");
            }
        }
        row = builder.substring(0, builder.length() - System.lineSeparator().length());
    }

    public String getRow() {
        return row;
    }

    public int getHeight() {
        return height;
    }
}
