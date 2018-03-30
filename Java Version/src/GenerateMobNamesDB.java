import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class GenerateMobNamesDB {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String stringsPath = "Resources/MobStrings.img.xml";
        File stringsFile = new File(stringsPath);
        String resultPath = "Result/Strings.csv";
        File database = new File(resultPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(stringsFile);
        doc.getDocumentElement().normalize();
        //Get children of ROOT element.
        NodeList nList = doc.getDocumentElement().getElementsByTagName("imgdir");

        FileWriter fileWriter = new FileWriter(database);

        fileWriter.write("id,name\n");

        for(int i = 0; i < nList.getLength(); i++) {
            Node imgnode = nList.item(i);
            String monster_id = imgnode.getAttributes().getNamedItem("name").getNodeValue();
            String monster_name = imgnode.getFirstChild().getAttributes().getNamedItem("value").getNodeValue();
            fileWriter.write(monster_id+","+monster_name+"\n");
        }

        fileWriter.close();
    }
}
