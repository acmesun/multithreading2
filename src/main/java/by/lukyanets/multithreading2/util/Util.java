package by.lukyanets.multithreading2.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Util {
    private static final Logger logger = LogManager.getLogger(Util.class);

    public static String findAbsolutePath(String fileName) {
        try {
            return Paths.get((Objects.requireNonNull(Util.class.getClassLoader().getResource(fileName))).toURI()).toString();
        } catch (URISyntaxException e) {
            logger.error("Exception in {}", fileName);
            throw new IllegalStateException(e);
        }
    }
}
