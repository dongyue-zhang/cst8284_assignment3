
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Scheduler {
    private static final Scanner scan = new Scanner(System.in);
    private ArrayList<Appointment> appointments = new ArrayList<>();

    private final int SAVE_APPOINTMENT = 1;
    private final int DELETE_APPOINTMENT = 2;
    private final int CHANGE_APPOINTMENT = 3;
    private final int DISPLAY_APPOINTMENT = 4;
    private final int DISPLAY_SCHEDULE = 5;
    private final int SAVE_APPOINTMENTS_TO_FILE = 6;
    private final int LOAD_APPOINTMENTS_FROM_FILE = 7;
    private final int EXIT = 0;

    private static Employee employee;

    public Scheduler(Employee emp) {}
    public Scheduler() {
        Dentist dentist = new Dentist("Dr. Andrews");
        System.out.println(dentist.toString());
        setEmployee(dentist);
    }

    public void launch() {
        while (true) {
            int choice = displayMenu();
            if (choice == EXIT) {break;}
            else {executeMenuItem(choice);}
        }
    }

    public static Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        Scheduler.employee = employee;
    }

    private int displayMenu() {
        return Integer.parseInt(getResponseTo("Enter a selection from the following menu:\n1. Save appointment\n" +
                "2. Remove appointment\n3. Change appointment\n4. Get appointment\n5. Display schedule\n" +
                "6. Backup appointments to file\n7. Load appointments from file\n0. Exit program\n"));
    }
    private void executeMenuItem (int choice)  {
        switch (choice) {
            case SAVE_APPOINTMENT:
                Appointment aptNew = makeAppointmentFromUserInput();
                boolean calendarAvailable = saveAppointment(aptNew);
                if (calendarAvailable) {
                    System.out.println("Appointment saved.");
                }
                else {
                    System.out.println("Cannot save; an appointment at that time already exist.");
                }
                break;
            case DELETE_APPOINTMENT:
                Calendar newCal = makeCalenderFromUserInput(false);
                System.out.println(findAppointment(newCal).toString());
                String r = getResponseTo("Enter 'Yes' to delete this appointment");
                if (r.equals("Yes")) {
                    if (deleteAppointment(newCal)) {
                        System.out.println("Appointment deleted.");
                    }
                }
                break;
            case CHANGE_APPOINTMENT:
                Calendar changeCal = makeCalenderFromUserInput(false);
                System.out.println(findAppointment(changeCal).toString());
                String response = getResponseTo("Enter 'Yes' to change the date and time of this appointment");
                if (response.equals("Yes")) {
                    if (changeAppointment(changeCal)) {
                        System.out.println("Appointment re-booked");
                    }

                }
                break;
            case DISPLAY_APPOINTMENT:
                Calendar calNew = makeCalenderFromUserInput(false);
                displayAppointment(calNew);
                break;
            case DISPLAY_SCHEDULE:
                Calendar displayCal = makeCalenderFromUserInput(true);
                displayDaySchedule(displayCal);
                break;
            case SAVE_APPOINTMENTS_TO_FILE:
                    if (saveAppointmentsToFile(getAppointments(), "CurrentAppointments.apts.txt")) {
                        System.out.println("Appointment data saved to\nCurrentAppointments.apts");
                    }
                break;
            case LOAD_APPOINTMENTS_FROM_FILE:
                if (loadAppointmentsFromFile("CurrentAppointments.apts.txt", getAppointments())) {
                    System.out.println("Appointments successfully loaded\nfrom CurrentAppointments.apts");
                }
                break;
        }

    }

    private boolean saveAppointment(Appointment apt) {
        if (findAppointment(apt.getAptDate()) != null) {
            return false;
        }
        else {
            //System.out.println(findAppointment(apt.getAptDate()));//
            System.out.println(apt);
            getAppointments().add(apt);
            for (Appointment element: getAppointments()) {
                System.out.println(element);
            }
            //System.out.println("!!!!");
            return true;
        }
    }
    private boolean deleteAppointment(Calendar cal) {
           return getAppointments().remove(findAppointment(cal));
    }
    private boolean changeAppointment(Calendar cal) {
        String dateNew = getResponseTo("Enter new date and time\nAppointment Date (entered as DDMMYYYY):");
        String timeInput = getResponseTo("Appointment Time: ");
        int timeNew = processTimeString(timeInput);
        if (findAppointment(cal) != null ) {
            findAppointment(cal).getAptDate().set(Integer.parseInt(dateNew.substring(4, 8)), Integer.parseInt(dateNew.substring(2, 4)) - 1, Integer.parseInt(dateNew.substring(0, 2)), timeNew, 0);
            return true;
        }
        else {
            return false;
        }
    }
    private void displayAppointment(Calendar cal) {
        if (findAppointment(cal) != null) {
            System.out.println(findAppointment(cal).toString());
        }
        else  {
            System.out.printf("No appointment scheduled between %s:00 and %s:00%n", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.HOUR_OF_DAY)+1);
        }
    }
    private void displayDaySchedule(Calendar cal) {
        for (int i = 8; i < 17; i++) {
            cal.set(Calendar.HOUR_OF_DAY, i);
            displayAppointment(cal);
        }
    }
    private boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
        File f = new File(saveFile);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f))){
            out.writeObject(apts);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean loadAppointmentsFromFile( String sourceFile, ArrayList<Appointment> apts) {
        File f = new File(sourceFile);
        if (f.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
                apts = (ArrayList<Appointment>) in.readObject();
                setAppointments(apts);
                return true;

            } catch (FileNotFoundException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return false;

    }

    public static String getResponseTo(String s) {
        System.out.print(s);
        return (scan.nextLine());
    }
    private static Appointment makeAppointmentFromUserInput() {
        String fullName = getResponseTo("Enter Client Name (as FirstName LastName): ");
        String phone = getResponseTo("Phone Number (e.g. 613-555-1212): ");
        Calendar cal = makeCalenderFromUserInput(false);

        String descriptionOfWork = getResponseTo("Enter Activity: ");
        Activity activity = new Activity();
        activity.setDescriptionOfWork(descriptionOfWork);
        String category = getEmployee().getActivityType();
        activity.setCategory(category);

        TelephoneNumber telephoneNumber = new TelephoneNumber(phone);
        return new Appointment(cal, fullName, telephoneNumber, activity);
    }

    private static Calendar makeCalenderFromUserInput(boolean suppressHours) {
        String date = getResponseTo("Appointment Date (entered as DDMMYYYY): ");
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2,4));
        int year = Integer.parseInt(date.substring(4,8));
        Calendar cal = Calendar.getInstance();
        cal.clear();
        if (suppressHours) {
            cal.set(year, month-1, day, 0, 0);
        }
        else {
            String time = getResponseTo("Appointment Time: ");
            int processedTime = processTimeString(time);
            cal.set(year, month-1, day, processedTime, 0);
        }
        return cal;
    }
    private static int processTimeString(String t) {
        if (t.contains(":00")) {
            t = t.replace(":00", "");
        }
        if (t.contains("p.m.")) {
            if (!t.contains("12")) {
                return Integer.parseInt(t.replace(" p.m.", "")) + 12;
            }
            else {
                return Integer.parseInt(t.replace(" p.m.", ""));
            }
        }
        else if (t.contains("a.m.") ) {
            return Integer.parseInt(t.replace(" a.m.", ""));
        }
        else if (Integer.parseInt(t) < 5) {
            return Integer.parseInt(t) + 12;
        }
        else {
            return Integer.parseInt(t);
        }
    }
        private Appointment findAppointment(Calendar cal) {
            for (Appointment element : getAppointments()) {
                if (element.getAptDate().equals(cal)) {
                    return element;
                }
            }
            return null;
        }

    private ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }
    private void setAppointments(ArrayList<Appointment> apts) {
        this.appointments = apts;
    }
}

