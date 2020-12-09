package epm.util;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
    public static synchronized List<Path> readAndRenameNewFiles(Path filePath, String mark) throws IOException {
        List<Path> newFiles = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(filePath, p -> !p.getFileName().toString().contains(mark))) {
            for (Path file : stream) {
                Path renamedFile = renameWithTime(file, mark);
                newFiles.add(renamedFile);
            }
        } catch (DirectoryIteratorException ex) {
            throw ex.getCause();
        }

        return newFiles;
    }

    public static List<String> readFile(Path path) {
        List<String> fileLines = new ArrayList<>();

        try {
            Stream<String> lines = Files.lines(path);
            fileLines.addAll(lines.collect(Collectors.toList()));
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileLines;
    }

    public static void moveFile(Path file, String toPath) {
        String filename = file.getFileName().toString();
        Path newPath = Paths.get("./" + toPath + "/" + filename);

        try {
            Files.move(file, newPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean pathExists(Path path) {
        return Files.exists(path);
    }

    private static Path renameWithTime(Path file, String mark) throws IOException {
        String filename = file.getFileName().toString();
        int idxOf = filename.indexOf(".txt");
        Instant instant = Instant.now();

        String renamed = filename.substring(0, idxOf) + mark + "_" + instant.getEpochSecond() + ".txt";
        return Files.move(file, file.resolveSibling(renamed));
    }
}
