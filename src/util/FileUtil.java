package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/data/movies.json");


    public static List<Movie> readFile() {
        try {
            String json = Files.readString(PATH);
            Movie[] movieArr = GSON.fromJson(json, Movie[].class);
            List<Movie> movies = new ArrayList<Movie>();
            for (Movie m : movieArr) {
                movies.add(m);
            }
            return movies;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
