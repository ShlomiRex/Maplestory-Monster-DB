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
import javax.xml.xpath.*;

public class GenerateMobStatsDB {
    static XPathFactory xPathFactory;
    static Document doc;
    static XPath xpath;
    static XPathExpression expr;
    static NodeList nl;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        String xml_files_location = "Resources/Mobs/";
        File files_dir = new File(xml_files_location);
        File[] files = files_dir.listFiles();

        String resultPath = "Result/MobStats.csv";
        File database = new File(resultPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        xPathFactory = XPathFactory.newInstance();
        FileWriter fileWriter = new FileWriter(database);
        fileWriter.write("id,level,exp,boss,HP,MP\n");
        for(File file : files) {
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            //monster id
            String monster_id = file.getName().substring(0,file.getName().indexOf('.'));


            //monster level
            Node n_level = getXPathResult("level");
            String monster_level = "";
            if(n_level != null)
                monster_level = n_level.getAttributes().getNamedItem("value").getNodeValue();


            //monster boss
            String monster_boss = "0";
            Node n_boss = getXPathResult("boss");
            if(n_boss != null)
                monster_boss = "1";


            //monster maxHP
            Node n_maxHP = getXPathResult("maxHP");
            String monster_maxHP = "";
            if(n_maxHP!=null)
                monster_maxHP = n_maxHP.getAttributes().getNamedItem("value").getNodeValue();


            //monster maxMP
            Node n_maxMP = getXPathResult("maxMP");
            String monster_maxMP = "";
            if(n_maxMP!=null)
                monster_maxMP = n_maxMP.getAttributes().getNamedItem("value").getNodeValue();


            //monster exp
            Node n_exp = getXPathResult("exp");
            String monster_exp = "";
            if(n_exp!=null)
                monster_exp = n_exp.getAttributes().getNamedItem("value").getNodeValue();

            fileWriter.write(monster_id+","+
                    monster_level+","+
                    monster_exp+","+
                    monster_boss+","+
                    monster_maxHP+","+
                    monster_maxMP+"\n");
        }
        fileWriter.close();
    }

    //not generic function
    //only works for int element inside imgdir info, returns first node
    public static Node getXPathResult(String attName) throws XPathExpressionException {
        xpath = xPathFactory.newXPath();
        expr = xpath.compile("imgdir/imgdir[@name=\"info\"]/" +
                "int[contains(@name,\""+attName+"\")]");
        nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nl.item(0);
    }
}
