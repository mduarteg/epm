import epm.event.BaseEvent;
import epm.event.SMSEvent;
import epm.rate.EventWorker;
import epm.util.EventType;
import epm.util.QueueManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEventWorker {
    @Test
    public void should_AddOneRatedEventToQueue_When_ConsumeOneBaseEvent() throws InterruptedException {
        QueueManager.rateQueue.put(createSMSEvent(false));

        EventWorker eventWorker = new EventWorker();
        eventWorker.rateEvent();

        int size = QueueManager.persistQueue.size();
        assertEquals(size, 1);
    }

    @Test
    public void should_AddOneRejectedEventToQueue_When_ConsumeOneRejectedEvent() throws InterruptedException {
        QueueManager.rateQueue.clear();
        QueueManager.rateQueue.put(createSMSEvent(true));

        EventWorker eventWorker = new EventWorker();
        eventWorker.rateEvent();

        BaseEvent ev = QueueManager.persistQueue.poll();

        assertTrue(ev.isRejected());
    }

    private BaseEvent createSMSEvent(boolean r) {
        return new SMSEvent(
                r ? EventType.REJECTED : EventType.SMS,
                r,
                0.0,
                LocalDateTime.now(),
                "123456",
                "654321",
                230,
                r ? "Rejected" : null
        );
    }
}
