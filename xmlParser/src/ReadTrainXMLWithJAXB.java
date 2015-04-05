import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

/**
 * Created by IVoitsekhovskyi on 05.04.2015.
 */
public class ReadTrainXMLWithJAXB {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {


        JAXBContext contex = JAXBContext.newInstance(CatalogTrains.class);
        Unmarshaller unmarshaller = contex.createUnmarshaller();

        File f = new File("xmlParser\\src\\trains.xml");
        CatalogTrains catalogTrains = (CatalogTrains) unmarshaller.unmarshal(f);

        //Все поезда
        System.out.println(catalogTrains);

        Calendar c1 = Calendar.getInstance();
        c1.set(2015, Calendar.APRIL, 5, 15, 0);

        Calendar c2 = Calendar.getInstance();
        c2.set(2015, Calendar.APRIL, 5, 16, 30);


        catalogTrains.printTrains(c1.getTime(),c2.getTime());

        //добавляем в файл
        catalogTrains.add(new Train(5, "odessa", "rivne", "05.04.2015", "17:30"));
        Marshaller marshaller = contex.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(catalogTrains,f);



    }
}
