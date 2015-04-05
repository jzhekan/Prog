import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IVoitsekhovskyi on 05.04.2015.
 */
@XmlRootElement(name="trains")
public class CatalogTrains {

    @XmlElement(name="train")
     private List<Train> trains = new ArrayList<>();

    public void add(Train train) {
        trains.add(train);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(trains.toArray());
    }

    public void printTrains(Date fromDate, Date tillDate){

        for (Train t: trains){

            Date dateD = t.getDateTimeDeparture();

              if(dateD.after(fromDate) & dateD.before(tillDate))
                  System.out.println(t);

        }
    }


}
