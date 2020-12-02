import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import epm.handler.FileHandler;
import epm.parse.EventParser;
import epm.util.QueueManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileHandler {
    @Test
    public void should_AddTwoLinesToQueue_When_ConsumeOneFile() throws InterruptedException, IOException {
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());
        Path dir = fileSystem.getPath("/foo");
        Files.createDirectory(dir);
        Path file = dir.resolve("test_file.txt");
        Files.write(file, ImmutableList.of("GSM,2025550163,18/09/2015 10:12:00,200,2025550147", "GPRS,2025550163,18/09/2015 10:12:00,200,2025550147"), StandardCharsets.UTF_8);

        QueueManager.fileQueue.put(file);

        FileHandler fileHandler = new FileHandler();
        fileHandler.processFile();

        int size = QueueManager.parseQueue.size();
        assertEquals(size, 2);
    }
}
