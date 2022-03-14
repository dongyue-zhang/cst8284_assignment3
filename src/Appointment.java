

import java.util.Calendar;

public class Appointment {
    private Calendar aptDate;
    private String firstName;
    private String lastName;
    private TelephoneNumber phone;
    private Activity activity;
    public static long serialVersionUID = 1L;

    public Appointment(Calendar aptDate, String fullName, TelephoneNumber phone, Activity activity) {
        this(aptDate,fullName.split(" ")[0], fullName.split(" ")[1], phone, activity);
    }
    public Appointment(Calendar aptDate, String firstName, String lastName, TelephoneNumber phone, Activity activity) {
        setAptDate(aptDate);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setActivity(activity);
    }

    public Calendar getAptDate() {
        return aptDate;
    }

    public void setAptDate(Calendar aptDate) {
        this.aptDate = aptDate;
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
