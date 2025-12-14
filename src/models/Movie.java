package models;

public class Movie {
    private String name;
    private int year;
    private String description;

    private class Director {
        private String fullName;
    }

    private class Cast {
        private String fullName;
        private String role;
    }

}
