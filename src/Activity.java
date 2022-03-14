

public class Activity {
    private String descriptionOfWork;
    private String category;

    public Activity() {}

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
