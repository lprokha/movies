package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path TRUCKSPATH = Paths.get("src/data/trucks.json");
    private static final Path DRIVERSPATH = Paths.get("src/data/drivers.json");


    private static String readFile(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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
