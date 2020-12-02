import epm.cache.CacheManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCacheManager {
    @Test
    public void should_LoadCache_When_Initializing() {
        CacheManager.initCache();
        CacheManager cache = CacheManager.getInstance();

        assertNotNull(cache.getEventRate("GSM"));
    }
}
