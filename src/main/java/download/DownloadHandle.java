package download;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DownLoadHandler {
    public static void main(String[] args) {
        DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            File inputFile = new File("pommaster.xml");
            Document doc = builder.parse(inputFile);
            NodeList node = doc.getElementsByTagName("dependency");
            List<DependencyClazz> dependencyClazzList= new ArrayList<>();
            for (int i = 0; i < node.getLength(); i++) {
                Node nNode = node.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) nNode;
                    String groupId = element.getElementsByTagName("groupId").item(0).getTextContent();
                    String artifactId = element.getElementsByTagName("artifactId").item(0).getTextContent();
                    String version = element.getElementsByTagName("version").item(0).getTextContent();
                    DependencyClazz article = new DependencyClazz(groupId,artifactId,version);
                    dependencyClazzList.add(article);
                }
            }
//            https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-web/2.7.0/spring-boot-starter-web-2.7.0.jar
            String urlBase ="https://repo1.maven.org/maven2/%s/%s/%s/%s";//group,Artifact,version,nameVer
            for (DependencyClazz dependency: dependencyClazzList
            ) {
                String group = dependency.getGroupId().replace(".","/");
                String nameVer = dependency.getArtifactId()+"-"+dependency.getVersion()+".jar";
                String linkComplete= String.format(urlBase,group,dependency.getArtifactId(),dependency.getVersion(),nameVer);
                new Thread(()->DownloadUtil.downloadFile(nameVer,linkComplete));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

}
