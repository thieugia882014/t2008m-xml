package dom;

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

class Article{
    private String title;
    private String description;
    private String link;

    public Article(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
public class App {
    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document doc = builder.parse("https://vnexpress.net/rss/giai-tri.rss");
            NodeList node = doc.getElementsByTagName("item");
            List<Article> articles= new ArrayList<>();
            for (int i = 0; i < node.getLength(); i++) {
                Node nNode = node.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) nNode;
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    String link = element.getElementsByTagName("link").item(0).getTextContent();
                    Article article = new Article(title,description,link);
                    articles.add(article);
                }
            }

            for (Article a: articles
                 ) {
                System.out.println("-------");
                System.out.println(a);

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
