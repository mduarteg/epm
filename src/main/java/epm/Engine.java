package epm;

import epm.cache.CacheManager;
import epm.handler.FileHandler;
import epm.listener.FileListener;
import epm.parse.EventParser;
import epm.persistence.EventPersistence;
import epm.rate.EventWorker;
import epm.util.PropertyManager;
import org.apache.log4j.Logger;

import java.util.concurrent.Executors;

public class Engine {

    private static final Logger logger = Logger.getLogger(Engine.class);

    public static void main(String[] args) {
        init();
        initCache();
    }

    private static void initCache() {
        CacheManager.initCache();
    }

    private static void init() {
        logger.info("Initializing properties");
        PropertyManager.loadProperties();
        initThreads();
    }

    private static void initThreads() {
        logger.info("Initializing threads");
        int listenerThreads = PropertyManager.getPropertyIntValue("epm.listener.threads");
        int handlerThreads = PropertyManager.getPropertyIntValue("epm.handler.threads");
        int parserThreads = PropertyManager.getPropertyIntValue("epm.parser.threads");
        int raterThreads = PropertyManager.getPropertyIntValue("epm.rater.threads");
        int persistThreads = PropertyManager.getPropertyIntValue("epm.persist.threads");

        for (int i = 0; i < listenerThreads; i++) {
            new Thread(new FileListener()).start();
        }

        for (int i = 0; i < handlerThreads; i++) {
            new Thread(new FileHandler()).start();
        }

        for (int i = 0; i < parserThreads; i++) {
            new Thread(new EventParser()).start();
        }

        for (int i = 0; i < raterThreads; i++) {
            new Thread(new EventWorker()).start();
        }

        for (int i = 0; i < persistThreads; i++) {
            new Thread(new EventPersistence()).start();
        }

    }

}
