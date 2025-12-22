package task3;

import task3.models.Actor;
import task3.models.Movie;
import task3.util.FileUtil;

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
        menu();
    }

    private static void menu() {
        List<Movie> movies = new ArrayList<>();
        Map<String, List<Movie>> actorsMap = new HashMap<>();
        Map<String, List<Movie>> directorsMap = new HashMap<>();
        Map<Integer, List<Movie>> yearsMap = new HashMap<>();

        List<Movie> currentList = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            System.out.println(
                    "\n\t======= MENU =======" +
                            "\n\t1. Load movies from file" +
                            "\n\t2. Show current list of movies" +
                            "\n\t3. Sort full list of movies" +
                            "\n\t4. Search movie by name" +
                            "\n\t5. Search movies by actor" +
                            "\n\t6. Search movies by director" +
                            "\n\t7. Search movies by year" +
                            "\n\t8. Show all actors with roles" +
                            "\n\t9. Sort current list" +
                            "\n\t0. Exit"
            );

            String choice;
            while (true) {
                System.out.print("Enter choice: ");
                choice = sc.nextLine().strip();
                if (choice.equals("0") || choice.equals("1") || choice.equals("2") || choice.equals("3") ||
                        choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7") || choice.equals("8") || choice.equals("9")) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter a number from 0 to 9.");
                }
            }

            switch (choice) {
                case "1":
                    movies = FileUtil.readFile();
                    currentList = new ArrayList<>(movies);
                    System.out.println("Movies loaded successfully!");
                    break;
                case "2":
                    if (!currentList.isEmpty()) {
                        printCollection(currentList);
                    } else {
                        System.out.println("Current list is empty!");
                    }
                    break;
                case "3":
                    sortListMenu(movies, "The");
                    break;
                case "4":
                    currentList = searchByName(createMapFromList(movies));
                    printCollection(currentList);
                    break;
                case "5":
                    currentList = searchActorWithRoles(movies, createActorsMap(currentList));
                    break;
                case "6":
                    currentList = searchByName(createDirectorsMap(movies));
                    printCollection(currentList);
                    break;
                case "7":
                    currentList = searchByYear(createYearsMap(movies));
                    printCollection(currentList);
                    break;
                case "8":
                    List<Map.Entry<String, List<String>>> allActors = getActorsListWithRoles(movies);
                    printActorsList(allActors);
                    break;
                case "9":
                    sortListMenu(currentList, "Current");
                    break;
                case "0":
                    System.out.println("Exiting...");
                    exit = true;
                    break;
            }
        }
    }

    private static void sortListMenu(List<Movie> list, String nameOfList) {
        if (list.isEmpty()) {
            System.out.printf("%n%s list is empty! Nothing to sort.%n", nameOfList);
        } else {
            String choice;
            while (true) {
                System.out.println("Sort by: 1. Name  2. Year  3. Director");
                choice = sc.nextLine().strip();
                if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                    break;
                }
                System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }

            Comparator<Movie> cmp;
            switch (choice) {
                case "1":
                    cmp = byName;
                    break;
                case "2":
                    cmp = byYear;
                    break;
                case "3":
                    cmp = byDirector;
                    break;
                default:
                    cmp = byName;
            }

            while (true) {
                System.out.println("Order: 1. Ascending  2. Descending");
                String order = sc.nextLine().strip();
                if (order.equals("1")) {
                    sortCollection(list, cmp);
                    break;
                } else if (order.equals("2")) {
                    sortReversedCollection(list, cmp);
                    break;
                } else {
                    System.out.println("Invalid order! Enter 1 or 2.");
                }
            }
        }
    }

    private static Map<String, List<Movie>> createMapFromList(List<Movie> movies) {
        Map<String, List<Movie>> map = new HashMap<>();
        for (Movie m : movies) {
            if (!map.containsKey(m.getName())) {
                map.put(m.getName(), new ArrayList<>());
            }
            map.get(m.getName()).add(m);
        }
        return map;
    }

    private static void printActorsList(List<Map.Entry<String, List<String>>> actorList) {
        for (Map.Entry<String, List<String>> entry : actorList) {
            System.out.println("Actor: " + entry.getKey() + " | Roles: " + entry.getValue());
        }
    }

    private static List<Map.Entry<String, List<String>>> getActorsListWithRoles(List<Movie> movies) {
        Map<String, List<String>> actorMap = new HashMap<>();

        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                actorMap.putIfAbsent(actor.getFullName(), new ArrayList<>());
                if (!actorMap.get(actor.getFullName()).contains(actor.getRole())) {
                    actorMap.get(actor.getFullName()).add(actor.getRole());
                }
            }
        }

        List<Map.Entry<String, List<String>>> actorList = new ArrayList<>(actorMap.entrySet());
        actorList.sort(Comparator.comparing(Map.Entry::getKey));

        return actorList;
    }

    private static List<Movie> searchActorWithRoles(List<Movie> movies,Map<String, List<Movie>> actorMap) {
        List<Movie> searchResults = new ArrayList<>();
        boolean notFound = true;

        System.out.print("Enter actor's name: ");
        while (notFound) {
            String search = sc.nextLine().strip().toLowerCase();

            for (String actor : actorMap.keySet()) {
                if (actor.toLowerCase().contains(search)) {
                    System.out.println("Actor: " + actor);
                    List<Movie> actorMovies = actorMap.get(actor);

                    for (Movie movie : actorMovies) {
                        for (Actor a : movie.getCast()) {
                            if (a.getFullName().equalsIgnoreCase(actor)) {
                                System.out.println("Movie: " + movie.getName() + " | Role: " + a.getRole());
                                searchResults.add(movie);
                                break;
                            }
                        }
                    }

                    notFound = false;
                }
            }
            if (notFound) {
                System.out.println("No actor found for \"" + search + "\". Try another name:");
            }
        }
        return searchResults;
    }

    private static List<Movie> searchByYear(Map<Integer, List<Movie>> map) {
        List<Movie> searchResults = new ArrayList<Movie>();
        int year = 0;

        System.out.print("Enter year to search: ");
        while (true) {
            try {
                year = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: a year can only contain digits!");
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
                    searchResults.addAll(map.get(name));
                    notFound = false;
                }
            }
            if (notFound) {
                System.out.println("No results found for \"" + search + "\". Try another name:");
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

    private static void printCollection(List<Movie> movies) {
        movies.forEach(System.out::println);
    }

}
