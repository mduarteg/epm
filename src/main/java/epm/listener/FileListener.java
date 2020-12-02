package epm.listener;

import epm.exception.PropertyException;
import epm.util.FileUtils;
import epm.util.PropertyManager;
import epm.util.QueueManager;

import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileListener implements Runnable {

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

        List<Path> newFiles = FileUtils.readAndRenameNewFiles(filePath, mark);

        for (Path file : newFiles) {
            QueueManager.fileQueue.put(file);
        }

        TimeUnit.MILLISECONDS.sleep(waitTime * 1000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                listen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
