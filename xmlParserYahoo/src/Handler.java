import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IVoitsekhovskyi on 05.04.2015.
 */

public class Handler extends DefaultHandler{

    private Map<String,Double> data  = new HashMap<>();
    private String id_rate;
    private Double rate;
    private String element;

    public Map<String, Double> getData() {
        return data;
    }

    @Override
    public void startDocument() throws SAXException {
         System.out.println("start parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
         System.out.println("end parsing...");
    }


    @Override
    public void startElement(String uri_namespace, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;
        if(element.equals("rate")){
            id_rate = attributes.getValue(0);
        }

    }

    @Override
    public void endElement(String uri_namespace, String localName, String qName) throws SAXException {

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(element.equals("Rate")) {
            String str = new String(ch, start, length);
            data.put(id_rate,Double.parseDouble(str));
        }

    }
}

