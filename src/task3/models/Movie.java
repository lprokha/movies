package task3.models;

import java.util.List;

public class Movie {
    private String name;
    private int year;
    private String description;

    private Director director;
    private List<Actor> cast;

    @Override
    public String toString() {
        return "Movie:" +
                "\n\tname: " + name +
                "\n\tyear: " + year +
                "\n\tdescription: " + description +
                "\n\tdirector: " + director +
                "\n\tcast: " + castToStr();
    }

    private String castToStr() {
        StringBuilder sb = new StringBuilder();
        cast.forEach(a -> sb.append("\t").append(a).append("\n"));
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirectorName() {
        return director.getFullName();
    }

    public List<Actor> getCast() {
        return cast;
    }
}
