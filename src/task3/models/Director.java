package task3.models;

public class Director {
    private String fullName;

    @Override
    public String toString() {
        return "\n\t\tname: " + fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
