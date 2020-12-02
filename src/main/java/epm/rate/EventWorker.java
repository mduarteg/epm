package epm.rate;

import epm.event.BaseEvent;
import epm.exception.RateDateOutOfRangeException;
import epm.rate.factory.SimpleRaterFactory;
import epm.util.QueueManager;
import org.apache.log4j.Logger;

public class EventWorker implements Runnable {

    private static final SimpleRaterFactory raterFactory = new SimpleRaterFactory();
    private static final Logger logger = Logger.getLogger(EventWorker.class);

    public void rateEvent() {
        BaseEvent event = QueueManager.rateQueue.poll();

        if (event != null && !event.isRejected()) {
            BaseEvent ratedEvent = null;

            try {
                ratedEvent = raterFactory.rateEvent(event);
            } catch (RateDateOutOfRangeException e) {
                ratedEvent = event;
                ratedEvent.setRejected(true);
                ratedEvent.setRejectionReason(e.getMessage());
            }

            logger.info("Rated event " + event.toString());

            try {
                QueueManager.persistQueue.put(ratedEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (event != null && event.isRejected()) {
            try {
                QueueManager.persistQueue.put(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while(true) {
            rateEvent();
        }
    }

}
