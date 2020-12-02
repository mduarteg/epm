import epm.event.BaseEvent;
import epm.parse.EventParser;
import epm.util.QueueManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEventParser {
    @Test
    public void should_AddOneEventToQueue_When_ConsumeOneLine() throws InterruptedException {
        QueueManager.parseQueue.put("GSM,2025550163,18/09/2015 10:12:00,200,2025550147");

        EventParser parser = new EventParser();
        parser.parseEvent();

        int size = QueueManager.rateQueue.size();
        assertEquals(size, 1);
    }

    @Test
    public void should_AddRejectedEventToQueue_When_LineIsInvalid() throws InterruptedException {
        QueueManager.rateQueue.clear();
        QueueManager.parseQueue.put("GSM,,18/09/2015 10:12:00,200,2025550147");

        EventParser parser = new EventParser();
        parser.parseEvent();

        BaseEvent evt = QueueManager.rateQueue.poll();
        assertTrue(evt.isRejected());
    }

    @Test
    public void should_HaveInvalidNumberOfValuesRejectedReason_When_LineHasLessThanFiveValues() throws InterruptedException {
        QueueManager.rateQueue.clear();
        QueueManager.parseQueue.put("102640358,20/01/2020 10:12:00,88905140360");

        EventParser parser = new EventParser();
        parser.parseEvent();

        BaseEvent evt = QueueManager.rateQueue.poll();
        assertEquals(evt.getRejectionReason(), "Event could not be parsed - The number of values is invalid");
    }

    @Test
    public void should_HaveDateInvalidRejectedReason_When_LineDateIsInvalid() throws InterruptedException {
        QueueManager.rateQueue.clear();
        QueueManager.parseQueue.put("GSM,102640358,180/09/2015 10:12:00,200,88905140360");

        EventParser parser = new EventParser();
        parser.parseEvent();

        BaseEvent evt = QueueManager.rateQueue.poll();
        assertEquals(evt.getRejectionReason(), "Event could not be parsed - Event date is invalid");
    }
}
