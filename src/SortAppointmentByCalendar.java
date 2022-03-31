import java.util.Calendar;
import java.util.Comparator;

public class SortAppointmentByCalendar implements Comparator<Appointment>{
    public int compare(Appointment a, Appointment b) {
        return a.getAptDate().compareTo(b.getAptDate());
    }
}
