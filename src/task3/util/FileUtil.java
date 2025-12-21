package task3.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import task3.models.Movie;
import task3.models.MovieCatalog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/task3.data/movies.json");


    public static List<Movie> readFile() {
        try {
            String json = Files.readString(PATH);
            MovieCatalog catalog = GSON.fromJson(json, MovieCatalog.class);
            return catalog.getMovies();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
