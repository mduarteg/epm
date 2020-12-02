package epm.util;

import epm.event.BaseEvent;
import epm.event.GPRSEvent;
import epm.event.GSMEvent;
import epm.event.SMSEvent;
import epm.model.RatedEvent;
import epm.model.RejectedEvent;

public class EventUtils {
    public static String getTargetResource(BaseEvent evt) {
        String target;

        switch (evt.getEventType()) {
            case GSM:
                target = ((GSMEvent) evt).getCaller();
                break;
            case GPRS:
                target = ((GPRSEvent) evt).getTargetResource();
                break;
            case SMS:
                target = ((SMSEvent) evt).getSender();
                break;
            default:
                target = null;
                break;
        }

        return target;
    }

    public static RatedEvent getRatedEvent(BaseEvent e) {
        return new RatedEvent(
                e.getEventType().toString(),
                getTargetResource(e),
                e.getStartTime(),
                e.getUnitsConsumed(),
                e.getTotalCharge()
        );
    }

    public static RejectedEvent getRejectedEvent(BaseEvent e) {
        return new RejectedEvent(
                e.getEventType().toString(),
                getTargetResource(e),
                e.getStartTime(),
                e.getUnitsConsumed(),
                e.getRejectionReason()
        );
    }

}
