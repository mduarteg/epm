import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import epm.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileUtils {
    @Test
    public void should_RenameFile_When_ReadFromPath() throws IOException {
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());
        Path dir = fileSystem.getPath("/foo");
        Files.createDirectory(dir);
        Path file = dir.resolve("test_file.txt");
        Files.write(file, ImmutableList.of("GSM,2025550163,18/09/2015 10:12:00,200,2025550147"), StandardCharsets.UTF_8);

        String mark = "_inProgress";

        List<Path> files = FileUtils.readAndRenameNewFiles(dir, mark);

        assertEquals(files.get(0).getFileName().toString(), "test_file_inProgress.txt");
    }

    @Test
    public void should_ReturnOneLine_When_ReadFromPath() throws IOException {
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());
        Path dir = fileSystem.getPath("/foo");
        Files.createDirectory(dir);
        Path file = dir.resolve("test_file.txt");
        Files.write(file, ImmutableList.of("GSM,2025550163,18/09/2015 10:12:00,200,2025550147"), StandardCharsets.UTF_8);

        List<String> files = FileUtils.readFile(file);

        assertEquals(files.get(0), "GSM,2025550163,18/09/2015 10:12:00,200,2025550147");
    }
}
