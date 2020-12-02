package epm.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"REJECTED_EVENT\"")
public class RejectedEvent {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="id", nullable = false)
    private UUID id;
    private String eventType;
    private String targetResource;
    private LocalDateTime eventStartTime;
    private int eventUnitConsumed;
    private String rejectionReason;

    public RejectedEvent() { }

    public RejectedEvent(String eventType, String targetResource, LocalDateTime eventStartTime, int eventUnitConsumed, String rejectionReason) {
        this.eventType = eventType;
        this.targetResource = targetResource;
        this.eventStartTime = eventStartTime;
        this.eventUnitConsumed = eventUnitConsumed;
        this.rejectionReason = rejectionReason;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTargetResource() {
        return targetResource;
    }

    public void setTargetResource(String targetResource) {
        this.targetResource = targetResource;
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public int getEventUnitConsumed() {
        return eventUnitConsumed;
    }

    public void setEventUnitConsumed(int eventUnitConsumed) {
        this.eventUnitConsumed = eventUnitConsumed;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Override
    public String toString() {
        return "RejectedEvent{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", targetResource='" + targetResource + '\'' +
                ", eventStartTime=" + eventStartTime +
                ", eventUnitConsumed=" + eventUnitConsumed +
                ", rejectionReason='" + rejectionReason + '\'' +
                '}';
    }
}
