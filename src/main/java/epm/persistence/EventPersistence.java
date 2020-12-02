package epm.persistence;

import epm.event.BaseEvent;
import epm.model.RatedEvent;
import epm.repository.RatedEventRepository;
import epm.util.EntityManagerUtils;
import epm.util.EventUtils;
import epm.util.PropertyManager;
import epm.util.QueueManager;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventPersistence implements Runnable {

    private final List<BaseEvent> eventsToPersist = Collections.synchronizedList(new ArrayList<>());

    private void persistEvent() {
        BaseEvent event = QueueManager.persistQueue.poll();
        int batchSize = PropertyManager.getPropertyIntValue("epm.persist.batch.size");

        if (event != null) {
            eventsToPersist.add(event);

            if (eventsToPersist.size() < batchSize) {
                return;
            }

            persistToDB();
        }
    }

    private void persistToDB() {
        RatedEventRepository repository = new RatedEventRepository();
        EntityManager em = EntityManagerUtils.getEntityManager();

        try {
            EntityManagerUtils.beginTransaction();

            repository.setEntityManager(em);

            for (BaseEvent e : eventsToPersist) {
                repository.save(getRatedEvent(e));
            }

            EntityManagerUtils.commit();
            eventsToPersist.clear();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            if (EntityManagerUtils.isActive()) {
                EntityManagerUtils.rollback();
            }
        }
    }

    private RatedEvent getRatedEvent(BaseEvent e) {
        return new RatedEvent(
                e.getEventType().toString(),
                EventUtils.getTargetResource(e),
                e.getStartTime(),
                e.getUnitsConsumed(),
                e.getTotalCharge()
        );
    }

    @Override
    public void run() {
        while(true) {
            persistEvent();
        }
    }

}
