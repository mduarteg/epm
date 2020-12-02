package epm.handler;

import epm.util.FileUtils;
import epm.util.PropertyManager;
import epm.util.QueueManager;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileHandler implements Runnable {

    public void processFile() {
        Path file;
        String processedPath = PropertyManager.getPropertyValue("epm.processed.events.location");

        try {
            file = QueueManager.fileQueue.poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        if (file == null) return;

        List<String> fileLines = FileUtils.readFile(file);

        for (String line : fileLines) {
            try {
                QueueManager.parseQueue.put(line);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        FileUtils.moveFile(file, processedPath);
    }

    @Override
    public void run() {
        while (true) {
            processFile();
        }
    }

}
