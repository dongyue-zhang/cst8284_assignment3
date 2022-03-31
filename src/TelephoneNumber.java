import java.io.Serializable;

public class TelephoneNumber implements Serializable {
    private int areaCode;
    private int prefix;
    private int lineNumber;

    public TelephoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.equals("")) {
            throw new BadAppointmentDataException("Empty or null value entered", "Must enter a value");
        }
        else if (!phoneNumber.matches("[2-9]{3}-[0-9]{3}-[0-9]{4}")) {
            if (phoneNumber.length() != 12) {
                throw new BadAppointmentDataException("Incorrect format", "Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number");
            }
            else if (phoneNumber.substring(0, 1).contains("0") || phoneNumber.substring(0, 1).contains("1") ) {
                throw new BadAppointmentDataException("Incorrect format", "Area code can’t start with a ‘0’ or a ‘1’");
            }
            else {
                throw new BadAppointmentDataException("Bad character(s) in input string", "Telephone numbers can only contain numbers or the character ‘-‘");}
        }
        else {
            int areaCode = Integer.parseInt(phoneNumber.split("-")[0]);
            int prefix = Integer.parseInt(phoneNumber.split("-")[1]);
            int lineNumber = Integer.parseInt(phoneNumber.split("-")[2]);
            setAreaCode(areaCode);
            setPrefix(prefix);
            setLineNumber(lineNumber);
        }
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {

        this.areaCode = areaCode;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {

        this.prefix = prefix;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String toString() {
        return String.format("(%s)%s-%s", getAreaCode(),getPrefix(),getLineNumber());
    }
}
