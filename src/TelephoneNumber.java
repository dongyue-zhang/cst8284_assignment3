

public class TelephoneNumber {
    private int areaCode;
    private int prefix;
    private int lineNumber;

    public TelephoneNumber(String phoneNumber) {
        String areaCode = phoneNumber.split("-")[0];
        String prefix = phoneNumber.split("-")[1];
        String lineNumber = phoneNumber.split("-")[2];
        setAreaCode(Integer.parseInt(areaCode));
        setPrefix(Integer.parseInt(prefix));
        setLineNumber(Integer.parseInt(lineNumber));

    }
    public TelephoneNumber() {}

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
