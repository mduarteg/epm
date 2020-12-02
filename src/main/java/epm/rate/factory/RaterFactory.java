package epm.rate.factory;

import epm.event.BaseEvent;
import epm.exception.RateDateOutOfRangeException;

public abstract class RaterFactory {

    public abstract Rater createRater(BaseEvent evt);

    public BaseEvent rateEvent(BaseEvent evt) throws RateDateOutOfRangeException {
        Rater rater = createRater(evt);
        rater.loadRaterData();

        return rater.rate();
    }

}