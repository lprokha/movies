import models.Movie;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        run();

    }

    private static void run() {
        List<Movie> movies = FileUtil.readFile();
        printCollection(movies);
        searchMovie(movies);
    }

    private static void sortCollection() {

    }

    private static void searchMovie(List<Movie> movies) {
        List<Movie> searchResults = new ArrayList<Movie>();
        boolean notFound = true;
        System.out.print("Enter movie name: ");
        while (notFound) {
            String search = sc.nextLine().strip().toLowerCase();
            for (Movie movie : movies) {
                if (movie.getName().toLowerCase().contains(search)) {
                    searchResults.add(movie);
                    notFound = false;
                }
            }
            if (notFound) {
                System.out.println("Movie \"" + search + "\" not found. Try another name:");
                continue;
            }
        }
        printCollection(searchResults);
    }

    private static void printCollection(List<Movie> movies) {
        movies.forEach(System.out::println);
    }

}
