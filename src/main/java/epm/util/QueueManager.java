package epm.util;

import epm.event.BaseEvent;

import java.nio.file.Path;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueManager {
    public static BlockingQueue<Path> fileQueue;
    public static BlockingQueue<BaseEvent> rateQueue;
    public static BlockingQueue<BaseEvent> persistQueue;
    public static BlockingQueue<String> parseQueue;

    static {
        fileQueue = new ArrayBlockingQueue<>(100);
        parseQueue = new ArrayBlockingQueue<>(5000);
        rateQueue = new ArrayBlockingQueue<>(5000);
        persistQueue = new ArrayBlockingQueue<>(5000);
    }
}