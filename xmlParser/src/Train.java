import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IVoitsekhovskyi on 05.04.2015.
 */
@XmlRootElement(name="train")
public class Train {


    private int id;
    private String from;
    private String to;
    private String dateDeparture;
    private String timeDeparture;


    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + getDateTimeDeparture() +
                '}';
    }



    public Train(){


    }

    public Train(int id, String from, String to, String dateDeparture, String timeDeparture) {

        this.id = id;
        this.from = from;
        this.to = to;
        this.dateDeparture = dateDeparture;
        this.timeDeparture = timeDeparture;

    }

    public String getFrom() {
        return from;
    }
    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }


    public int getId() {

        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return dateDeparture;
    }

    @XmlElement(name = "date")
    public void setDate(String date) {
        this.dateDeparture = date;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    @XmlElement(name = "departure")
    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Date getDateTimeDeparture(){

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date date = null;
        try {
            date = format.parse(dateDeparture + " " +timeDeparture);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
}
