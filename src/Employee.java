import java.io.Serializable;

public abstract class Employee implements Serializable {
    private String fullName;

    protected Employee() {}
    protected Employee(String name) {
        setFullName(name);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public abstract String getActivityType();
    public String toString() {
        return String.format("Scheduling appointments for %s", getFullName());
    }
}

