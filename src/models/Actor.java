package models;

public class Actor {
    private String fullName;
    private String role;

    @Override
    public String toString() {
        return "\n\t\tname: " + fullName + "\n\t\trole: " + role + "\n";
    }
}
