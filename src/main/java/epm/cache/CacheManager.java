package epm.cache;

import epm.model.EventRate;
import epm.repository.EventRateRepository;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CacheManager {

    private static final Logger logger = Logger.getLogger(CacheManager.class);
    private static EventRateCache eventRateCache;
    private static volatile CacheManager INSTANCE;

    private CacheManager() {
        eventRateCache = new EventRateCache(1000);
        loadEventRateData();
    }

    public static CacheManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheManager();
        }

        return INSTANCE;
    }

    public static void initCache() {
        logger.info("Initializing Event Rate cache");

        if (INSTANCE == null) {
            INSTANCE = new CacheManager();
        }
    }

    public static void refreshCache() {
        logger.info("Refreshing Event Rate cache");

        loadEventRateData();
    }

    public List<EventRate> getEventRate(String k) {
        return eventRateCache.findAll(k);
    }

    private static void loadEventRateData() {
        eventRateCache.clear();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epm");
        EntityManager em = emf.createEntityManager();

        EventRateRepository repo = new EventRateRepository();
        repo.setEntityManager(em);
        Iterable<EventRate> all = repo.findAll();

        for(EventRate ev : all) {
            logger.info("Adding value with key: " + ev.getEventType() + "_" + ev.getEffectiveDate());
            eventRateCache.set(ev.getEventType() + "_" + ev.getEffectiveDate(), ev);
        }
    }
}
