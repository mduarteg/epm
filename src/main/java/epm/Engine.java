package epm;

import epm.cache.CacheManager;
import epm.handler.FileHandler;
import epm.listener.FileListener;
import epm.parse.EventParser;
import epm.rate.EventWorker;
import epm.util.PropertyManager;
import org.apache.log4j.Logger;

public class Engine {

    private static final Logger logger = Logger.getLogger(Engine.class);

    public static void main(String[] args) {
        init();
        initCache();

        for (int i = 0; i < 4; i++) {
            new Thread(new FileListener()).start();
            new Thread(new FileHandler()).start();
            new Thread(new EventParser()).start();
            new Thread(new EventWorker()).start();
        }
    }

    private static void initCache() {
        CacheManager.initCache();
    }

    private static void init() {
        logger.info("Initializing properties");
        PropertyManager.loadProperties();
    }

}
