package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/data/movies.json");


    private static Movie[] readFile(Path path) {
        try {
            String json = Files.readString(path);
            return GSON.fromJson(json, Movie[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Movie[0];
    }

    private static void writeToFile(Path path, String json) {
        byte[] strToBytes = json.getBytes();

        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static Truck[] readTrucks() {
//            return GSON.fromJson(readFile(TRUCKSPATH), Truck[].class);
//    }
//
//    public static Driver[] readDrivers() {
//        return GSON.fromJson(readFile(DRIVERSPATH), Driver[].class);
//    }
//
//    public static void writeTrucks(Truck[] trucks) {
//       writeToFile(TRUCKSPATH, GSON.toJson(trucks));
//    }
//
//    public static void writeDrivers(Driver[] drivers) {
//        writeToFile(DRIVERSPATH, GSON.toJson(drivers));
//    }
}
