import java.util.ArrayList;

public class Dentist extends Employee{
    private ArrayList<String> activities = new ArrayList<String>();


    public Dentist(String fullName) {
            super(fullName);
    }

    public String getActivityType() {
        activities.add("Assessment");
        activities.add("Filling");
        activities.add("Crown");
        activities.add("Cosmetic Repairs");
        int number = Integer.parseInt(Scheduler.getResponseTo("1. Assessment\n2. Filling\n3. Crown\n4. Cosmetic Repairs\n"));
        for (int i = 0; i < activities.size(); i++) {
            if ( i+1 == number) {
                return activities.get(i);
            }
        }
        return null;
    }
}
