import models.Actor;
import models.Movie;
import util.FileUtil;

import javax.crypto.spec.PSource;
import java.util.*;

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
//        searchMovie(movies);

//        Map<String, List<Movie>> actors = createActorsMap(movies);
//        printCollection(searchByName(actors));
//
//        Map<String, List<Movie>> directors = createDirectorsMap(movies);
//        printCollection(searchByName(directors));

        Map<Integer, List<Movie>> years = createYearsMap(movies);
        printCollection(searchByYear(years));


//        System.out.println("Сортировка по году:");
//        sortCollection(movies, byYear);
//        sortReversedCollection(movies, byYear);
//
//        System.out.println("Сортировка по названию:");
//        sortCollection(movies, byName);
//        sortReversedCollection(movies, byName);
//
//        System.out.println("Сортировка по режиссеру:");
//        sortCollection(movies, byDirector);
//        sortReversedCollection(movies, byDirector);
    }

    private static List<Movie> searchByYear(Map<Integer, List<Movie>> map) {
        List<Movie> searchResults = new ArrayList<Movie>();
        int year = 0;

        System.out.print("Enter year to search: ");
        while (true) {
            try {
                year = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: a year can only contain digits!");
                sc.nextLine();
                continue;
            }

            if (map.containsKey(year)) {
                searchResults.addAll(map.get(year));
                return searchResults;
            } else {
                System.out.println("No results found for \"" + year + "\". Try another year:");
            }
        }
    }

    private static List<Movie> searchByName(Map<String, List<Movie>> map) {
        List<Movie> searchResults = new ArrayList<Movie>();
        boolean notFound = true;

        System.out.print("Enter name to search: ");
        while (notFound) {
            String search = sc.nextLine().strip().toLowerCase();

            for (String name : map.keySet()) {
                if (name.toLowerCase().contains(search)) {
                    System.out.println("Name: " + name);
                    searchResults.addAll(map.get(name));
                    notFound = false;
                }
            }
            if (notFound) {
                System.out.println("No results found for \"" + search + "\". Try another name:");
                continue;
            }
        }
        return searchResults;
    }

    private static Map<String, List<Movie>> createActorsMap(List<Movie> movies) {
        Map<String, List<Movie>> actors = new HashMap<>();

        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                String name = actor.getFullName();

                if (!actors.containsKey(name)) {
                    actors.put(actor.getFullName(), new ArrayList<Movie>());
                }

                actors.get(name).add(movie);
            }
        }
        return actors;
    }

    private static Map<String, List<Movie>> createDirectorsMap(List<Movie> movies) {
        Map<String, List<Movie>> directors = new HashMap<>();

        for (Movie movie : movies) {
            String name = movie.getDirectorName();

            if (!directors.containsKey(name)) {
                directors.put(name, new ArrayList<Movie>());
            }

            directors.get(name).add(movie);
        }
        return directors;
    }

    private static Map<Integer, List<Movie>> createYearsMap(List<Movie> movies) {
        Map<Integer, List<Movie>> years = new HashMap<>();

        for (Movie movie : movies) {
            int year = movie.getYear();

            if (!years.containsKey(year)) {
                years.put(year, new ArrayList<Movie>());
            }

            years.get(year).add(movie);
        }
        return years;
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
