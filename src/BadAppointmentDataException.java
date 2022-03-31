public class BadAppointmentDataException extends RuntimeException{
    private String description;

    public BadAppointmentDataException (String s1, String s2) {
        super(s1);
        setDescription(s2);

    }
    public BadAppointmentDataException() {
        this("Please try again", "Bas data entered");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
