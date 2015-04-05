import java.time.LocalTime;
import java.util.Date;

/**
 * Created by LUDA on 02.04.2015.
 */
public class WorkDate {

    public static void main(String[] args) {
        Date d1 = new Date();
        System.out.println(d1);

        Date d2 = new Date();
        d2.setTime(d1.getTime()+100);
        System.out.println(d2);
        System.out.println(d2.after(d1));
        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);

        System.out.println(d1);
    }
}
