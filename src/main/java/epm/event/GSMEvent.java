package epm.event;

import epm.util.EventType;

import java.time.LocalDateTime;
import java.util.Date;

public class GSMEvent extends BaseEvent {
    private String caller;
    private String callee;
    private int callDuration;

    public GSMEvent(EventType eventType, boolean isRejected, double totalCharge, LocalDateTime startTime, String caller, String callee, int callDuration, String rejectionReason) {
        super(eventType, isRejected, totalCharge, startTime, rejectionReason);
        this.caller = caller;
        this.callee = callee;
        this.callDuration = callDuration;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    @Override
    public String toString() {
        return "GSMEvent{" +
                "caller='" + caller + '\'' +
                ", callee='" + callee + '\'' +
                ", callDuration=" + callDuration +
                " " + super.toString() +
                '}';
    }
}