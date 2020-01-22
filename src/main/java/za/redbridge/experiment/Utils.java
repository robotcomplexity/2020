package za.redbridge.experiment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {

    public static String date;

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    /** Check if a String is either null or empty. */
    public static boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * Reads a serialized object instance from a file.
     * @param filepath the String representation of the path to the file
     * @return the deserialized object, or null if the object could not be deserialised
     */
    public static Object readObjectFromFile(String filepath) {
        Path path = Paths.get(filepath);
        Object object = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            object = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Unable to load object from file", e);
        }
        return object;
    }

    public static void saveObjectToFile(Serializable object, String filepath) {
        Path path = Paths.get(filepath);
        saveObjectToFile(object, path);
    }

    public static void saveObjectToFile(Serializable object, Path path) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
            out.writeObject(object);
        } catch (IOException e) {
            log.error("Unable to save object to file", e);
        }
    }

    public static Path getLoggingDirectory(String type, String config) {

        config = config.replace("config/","").replace(".yml","");
        String name = type+" ("+config+")";

        return Paths.get("results", name +"-"+date);
    }

    public static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (IOException e) {
            log.error("Unable to query host name", e);
        }
        return null;
    }

}
