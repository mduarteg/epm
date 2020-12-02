package epm.rate.factory;

import epm.event.BaseEvent;

public interface Rater {
    void loadRaterData();
    BaseEvent rate();
}
