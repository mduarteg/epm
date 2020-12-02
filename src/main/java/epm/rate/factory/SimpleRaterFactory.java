package epm.rate.factory;

import epm.event.BaseEvent;

public class SimpleRaterFactory extends RaterFactory {
    @Override
    public Rater createRater(BaseEvent evt) {
        return new SimpleRater(evt);
    }
}
