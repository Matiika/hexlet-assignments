package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String resultParth) throws IOException {

        CompletableFuture<String> futureFile1 = CompletableFuture.supplyAsync(() -> {
            String content1;
            try {
                Path path = Paths.get(path1);
                content1 = Files.readString(path);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return content1;
        });

        CompletableFuture<String> futureFile2 = CompletableFuture.supplyAsync(() -> {
            String content2;
            try {
                Path path = Paths.get(path2);
                content2 = Files.readString(path);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return content2;
        });

        return futureFile1.thenCombine(futureFile2, (content1, content2) -> {
            Path pathToFile = Paths.get(resultParth);
            try {
                Files.writeString(pathToFile, (content1 + content2));
                return content1 + content2;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });

    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("file1.txt", "file2.txt", "dest.txt");
        // END
    }
}

