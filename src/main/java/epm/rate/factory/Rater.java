package epm.rate.factory;

import epm.event.BaseEvent;
import epm.exception.RateDateOutOfRangeException;

public interface Rater {
    void loadRaterData() throws RateDateOutOfRangeException;
    BaseEvent rate();
}
