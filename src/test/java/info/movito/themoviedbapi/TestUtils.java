package info.movito.themoviedbapi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class TestUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String getFixture(String path) throws IOException {
        File file = getFileFromClasspath(path);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static <T> T getParsedFixture(String path, Class<T> clazz) throws IOException {
        File file = getFileFromClasspath(path);
        return objectMapper.readValue(file, clazz);
    }

    private static File getFileFromClasspath(String path) {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
    }
}
