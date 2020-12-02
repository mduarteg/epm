package epm.event;
import epm.util.EventType;

import java.time.LocalDateTime;
import java.util.Date;

public class SMSEvent extends BaseEvent {
    private String sender;
    private String receiver;
    private Integer numberOfChars;

    public SMSEvent(EventType eventType, boolean isRejected, double totalCharge, LocalDateTime startTime, String sender, String receiver, Integer numberOfChars, String rejectionReason) {
        super(eventType, isRejected, totalCharge, startTime, rejectionReason);
        this.sender = sender;
        this.receiver = receiver;
        this.numberOfChars = numberOfChars;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getNumberOfChars() {
        return numberOfChars;
    }

    public void setNumberOfChars(Integer numberOfChars) {
        this.numberOfChars = numberOfChars;
    }

    @Override
    public String toString() {
        return "SMSEvent{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", numberOfChars=" + numberOfChars +
                " " + super.toString() +
                '}';
    }
}