import java.io.Serializable;

public class Activity implements Serializable {
    private String descriptionOfWork;
    private String category;

    public Activity(String category, String descriptionOfWork) {
        setCategory(category);
        setDescriptionOfWork(descriptionOfWork);
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescriptionOfWork() {
        return descriptionOfWork;
    }


    public String getCategory() {
        return category;
    }

    public String toString() {
        return String.format("%s%n%s", getCategory(), getDescriptionOfWork());
    }
}
