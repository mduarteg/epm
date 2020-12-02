package epm.rate.factory;

import epm.cache.CacheManager;
import epm.event.BaseEvent;
import epm.event.GPRSEvent;
import epm.event.GSMEvent;
import epm.event.SMSEvent;
import epm.model.EventRate;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class SimpleRater implements Rater {

    private BaseEvent eventToRate;
    private EventRate eventRater;

    public SimpleRater(BaseEvent evt) {
        eventToRate = evt;
    }

    @Override
    public void loadRaterData() {
        List<EventRate> rateInfo = CacheManager.getInstance().getEventRate(eventToRate.getEventType().toString());
        LocalDateTime eventStartTime = eventToRate.getStartTime();

        rateInfo.sort(Comparator.comparing(EventRate::getEffectiveDate));

        for (EventRate e : rateInfo) {
            if (eventStartTime.isAfter(e.getEffectiveDate())) {
                eventRater = e;
                break;
            }
        }
    }

    @Override
    public BaseEvent rate() {
        int unitsConsumed = getConsumption() / eventRater.getUnitAmount();
        eventToRate.setUnitsConsumed(unitsConsumed);

        double totalCharge = unitsConsumed * eventRater.getUnitRate();
        eventToRate.setTotalCharge(totalCharge);

        return eventToRate;
    }

    private int getConsumption() {
        int consumed;

        switch (eventToRate.getEventType()) {
            case GSM:
                consumed = ((GSMEvent) eventToRate).getCallDuration();
                break;
            case GPRS:
                consumed = ((GPRSEvent) eventToRate).getDataConsumed();
                break;
            case SMS:
                consumed = ((SMSEvent) eventToRate).getNumberOfChars();
                break;
            default:
                consumed = 0;
                break;
        }

        return consumed;
    }

    public BaseEvent getEventToRate() {
        return eventToRate;
    }

    public void setEventToRate(BaseEvent eventToRate) {
        this.eventToRate = eventToRate;
    }

}