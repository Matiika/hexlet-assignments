package exercise;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) throws IOException {
        String carToStr = car.serialize();

        try {
            // Write the content to the file
            Files.write(path, carToStr.getBytes());
            System.out.println("Data written to file successfully!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static Car extract(Path path) throws IOException {
        // Read all bytes from the file
        byte[] bytes = Files.readAllBytes(path);

        // Convert bytes to a String
        String content = new String(bytes, StandardCharsets.UTF_8);

        return Car.deserialize(content);
    }
}
// END
