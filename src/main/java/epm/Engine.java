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

        Executors.newFixedThreadPool(listenerThreads).execute(new FileListener());
        Executors.newFixedThreadPool(handlerThreads).execute(new FileHandler());
        Executors.newFixedThreadPool(parserThreads).execute(new EventParser());
        Executors.newFixedThreadPool(raterThreads).execute(new EventWorker());
        Executors.newFixedThreadPool(persistThreads).execute(new EventPersistence());
    }

}
