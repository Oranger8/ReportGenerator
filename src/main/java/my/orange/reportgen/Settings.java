package my.orange.reportgen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Settings {

    private int pageWidth, pageHeight;
    private List<Column> columns;

    public int getPageWidth() {
        return pageWidth;
    }

    public int getPageHeight() {
        return pageHeight;
    }

    public List<Column> getColumns() {
        return columns;
    }

    Settings(String file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(file);
        Element root = document.getDocumentElement();
        if (!root.getTagName().equals("settings")) throw new SAXException();

        NodeList list = root.getElementsByTagName("page").item(0).getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() != Node.TEXT_NODE) {
                if (list.item(i).getNodeName().equals("width"))
                    pageWidth = Integer.parseInt(list.item(i).getTextContent());
                if (list.item(i).getNodeName().equals("height"))
                    pageHeight = Integer.parseInt(list.item(i).getTextContent());
            }
        }

        columns = new ArrayList<>();
        NodeList cols = root.getElementsByTagName("column");
        for (int i = 0; i < cols.getLength(); i++) {
            list = cols.item(i).getChildNodes();
            String title = null;
            int width = 0;
            for (int j = 0; j < list.getLength(); j++) {
                if (list.item(j).getNodeName().equals("title"))
                    title = list.item(j).getTextContent();
                if (list.item(j).getNodeName().equals("width"))
                    width = Integer.parseInt(list.item(j).getTextContent());
            }
            columns.add(new Column(title, width));
        }

    }

    class Column {

        private String title;
        private int width;

        Column(String title, int width) {
            this.title = title;
            this.width = width;
        }

        public String getTitle() {
            return title;
        }

        public int getWidth() {
            return width;
        }
    }
}
