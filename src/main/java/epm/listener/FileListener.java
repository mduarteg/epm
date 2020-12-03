package epm.listener;

import epm.exception.InputPathException;
import epm.exception.PropertyException;
import epm.util.FileUtils;
import epm.util.PropertyManager;
import epm.util.QueueManager;
import org.apache.log4j.Logger;

import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileListener implements Runnable {

    private static final Logger logger = Logger.getLogger(FileListener.class);

    public void listen() throws Exception {
        String path = PropertyManager.getPropertyValue("epm.input.events.location");
        String sleepTime = PropertyManager.getPropertyValue("epm.sleep.time");
        String mark = PropertyManager.getPropertyValue("epm.ip.mark");

        if (path == null || sleepTime == null || mark == null) {
            throw new PropertyException(
                    String.format(
                            "A mandatory property is missing - InputPath: %s - SleepTime: %s - InProgressMark: %s",
                            path,
                            sleepTime,
                            mark));
        }

        int waitTime = Integer.parseInt(sleepTime);
        Path filePath = Paths.get("./" + path);

        if (!inputPathExists(filePath)) {
            sleepThread(waitTime);
            throw new InputPathException(String.format("Files input directory does not exists - %s", path));
        }

        List<Path> newFiles = FileUtils.readAndRenameNewFiles(filePath, mark);

        for (Path file : newFiles) {
            QueueManager.fileQueue.put(file);
        }

        sleepThread(waitTime);
    }

    private void sleepThread(int sleepTime) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(sleepTime * 1000);
    }

    private boolean inputPathExists(Path path) {
        return FileUtils.pathExists(path);
    }

    @Override
    public void run() {
        while (true) {
            try {
                listen();
            } catch (Exception e) {
                logger.error(String.format("File listener error - %s", e.getMessage()));
            }
        }
    }

}
