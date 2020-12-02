package epm.rate.factory;

import epm.event.BaseEvent;

public abstract class RaterFactory {

    public abstract Rater createRater(BaseEvent evt);

    public BaseEvent rateEvent(BaseEvent evt) {
        Rater rater = createRater(evt);
        rater.loadRaterData();

        return rater.rate();
    }

}