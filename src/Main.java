import models.Movie;
import util.FileUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Comparator<Movie> byYear = Comparator.comparingInt(Movie::getYear);
    private static Comparator<Movie> byName = Comparator.comparing(Movie::getName);
    private static Comparator<Movie> byDirector = Comparator.comparing(Movie::getDirectorName);

    public static void main(String[] args) {

        run();

    }

    private static void run() {
        List<Movie> movies = FileUtil.readFile();
        printCollection(movies);
        searchMovie(movies);


        System.out.println("Сортировка по году:");
        sortCollection(movies, byYear);
        sortReversedCollection(movies, byYear);

        System.out.println("Сортировка по названию:");
        sortCollection(movies, byName);
        sortReversedCollection(movies, byName);

        System.out.println("Сортировка по режиссеру:");
        sortCollection(movies, byDirector);
        sortReversedCollection(movies, byDirector);
    }

    private static void sortCollection(List<Movie> movies, Comparator<Movie> cmp) {
        movies.sort(cmp);
        printCollection(movies);
    }

    private static void sortReversedCollection(List<Movie> movies, Comparator<Movie> cmp) {
        movies.sort(cmp.reversed());
        printCollection(movies);
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
