package epm.event;

import epm.util.EventType;

import java.time.LocalDateTime;

public class BaseEvent {
    private EventType eventType;
    private boolean isRejected;
    private double totalCharge;
    private int unitsConsumed;
    private LocalDateTime startTime;
    private String rejectionReason;

    public BaseEvent(EventType eventType, boolean isRejected, double totalCharge, LocalDateTime startTime, String rejectionReason) {
        this.eventType = eventType;
        this.isRejected = isRejected;
        this.totalCharge = totalCharge;
        this.startTime = startTime;
        this.rejectionReason = rejectionReason;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "eventType=" + eventType +
                ", isRejected=" + isRejected +
                ", totalCharge=" + totalCharge +
                ", unitsConsumed=" + unitsConsumed +
                ", startTime=" + startTime +
                ", rejectionReason='" + rejectionReason + '\'' +
                '}';
    }
}