import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by IVoitsekhovskyi on 05.04.2015.
 */

public class Yahoo {

    public static void main(String[] args) throws Exception {

        String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";
        String result = performRequest(request);


        FileWriter writeFile = null;
        File answerYahoo = new File("src\\answer.xml");

        try {
            writeFile = new FileWriter(answerYahoo);
            writeFile.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writeFile != null) {
                try {
                    writeFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        domParser(answerYahoo);

        saxParser(answerYahoo);

    }

    private static String performRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            char[] buf = new char[1000000];

            int r = 0;
            do {
                if ((r = br.read(buf)) > 0)
                    sb.append(new String(buf, 0, r));
            } while (r > 0);
        } finally {
            http.disconnect();
        }

        return sb.toString();
    }

    //DOM-parser
    static void domParser(File file) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(file);

        Element root = document.getDocumentElement();
        System.out.println("Root Element : " + root.getNodeName());
        System.out.println("----------------------");

        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {


                Element element = (Element) node;

                NodeList listRezults = element.getChildNodes();

                for (int i1 = 0; i1 < listRezults.getLength(); i1++) {

                    Node node2 = nodeList.item(i);
                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;

                        System.out.println("Name: " + element2.getElementsByTagName("Name").item(0)
                                .getChildNodes().item(0).getNodeValue());

                        System.out.println("Rate: " + element2.getElementsByTagName("Rate").item(0)
                                .getChildNodes().item(0).getNodeValue());

                        System.out.println("----------------------");
                    }
                }

            }
        }

    }

    //SAX-parser
    static void saxParser(File file){

        SAXParserFactory  parserF = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        try {
            SAXParser saxpraser  = parserF.newSAXParser();
            saxpraser.parse(file,handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(handler.getData());

    }


}
