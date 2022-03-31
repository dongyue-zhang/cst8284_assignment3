
import java.io.Serializable;
import java.util.Calendar;

public class Appointment implements Serializable {
    private Calendar aptDate;
    private String firstName;
    private String lastName;
    private TelephoneNumber phone;
    private Activity activity;
    public static long serialVersionUID = 1L;

    public Appointment(Calendar aptDate, String fullName, TelephoneNumber phone, Activity activity) {
        setAptDate(aptDate);
        setFullName(fullName);
        setPhone(phone);
        setActivity(activity);
    }
    public Appointment(Calendar aptDate, String firstName, String lastName, TelephoneNumber phone, Activity activity) {
        this(aptDate, firstName+ " " + lastName, phone, activity);
    }

    public Calendar getAptDate() {
        return aptDate;
    }

//    public void setAptDate(String aptDate) {
//        if (aptDate.matches("/d+")) {
//            if (aptDate.length() == 8) {
//                Year year = Year.of(Integer.parseInt(aptDate.substring(4, 8)));
//                int month = Integer.parseInt(aptDate.substring(2, 4)) + 1;
//                int day = Integer.parseInt(aptDate.substring(0, 2));
//                if ((day > 0 && day <= 31) && (month > 0 && month <= 12) && year.compareTo(Year.now()) >= 0) {
//                    boolean isSmallMonth = (month == Month.DECEMBER.getValue() || month == Month.JUNE.getValue() || month == Month.SEPTEMBER.getValue() || month == Month.NOVEMBER.getValue());
//                    boolean isBigMonth = (!isSmallMonth && month != Month.FEBRUARY.getValue());
//                    if (isBigMonth || (day <= 30 && isSmallMonth) || (year.isLeap() && day <= 29) || (!year.isLeap() && (day <= 28))) {
//                       setAptDate(year.getValue(), month, day, 0);
//                    }
//                    else {
//                        throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY");
//                    }
//
//                }
//                else {
//                    throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY");
//                }
//            }
//            else {
//                throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY");
//            }
//
//        }
//    }
//    public void setAptDate(int year, int month, int day, int hour) {
//        Calendar cal = Calendar.getInstance();
//        cal.clear();
//        cal.set(year, month, day, hour, 0);
//        setAptDate(cal);
//    }
    public void setAptDate(Calendar cal) {
        this.aptDate = cal;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFullName(String fullName) {
        if (fullName.equals("")) {
            throw new BadAppointmentDataException("Empty or null value entered", "Must enter a value") ;
        }
        else if (!fullName.matches("[a-zA-Z]+ [a-zA-Z]+")) {
            throw new BadAppointmentDataException("Illegal characters in name", "Name cannot include characters other than alphabetic characters, " +
                    "the dash (-), the period (.), and the apostrophe (‘)");
        }
        if (fullName.length() > 30) {
            throw new BadAppointmentDataException("Name exceeds maximum length", "Name cannot exceed 30 characters");
        }
        else {
            setFirstName(fullName.split(" ")[0]);
            setLastName(fullName.split(" ")[1]);
        }
    }

    public TelephoneNumber getPhone() {
        return phone;
    }

    public void setPhone(TelephoneNumber phone) {
        this.phone = phone;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String toString() {
        return String.format("%s%n%s %s%n%s%n%s%n", getAptDate().getTime(), getFirstName(), getLastName(), getPhone().toString(), getActivity().toString() );
    }
}
