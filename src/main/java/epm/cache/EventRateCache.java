package epm.cache;

import epm.model.EventRate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventRateCache extends LinkedHashMap<String, EventRate> {
    private final int capacity;

    public EventRateCache(int capacity) {
        super(capacity + 1, 1.0f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, EventRate> eldest) {
        return size() > this.capacity;
    }

    public EventRate find(String key) {
        return super.get(key);
    }

    public List<EventRate> findAll(String key) {
        List<Map.Entry<String, EventRate>> coincidences = super.entrySet()
                .stream()
                .filter(m -> m.getKey().contains(key))
                .collect(Collectors.toList());

        return coincidences.stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public void set(String key, EventRate value) {
        super.put(key, value);
    }
}
