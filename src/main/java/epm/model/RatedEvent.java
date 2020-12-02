package epm.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"RATED_EVENT\"")
public class RatedEvent {

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
    private double totalCharge;

    public RatedEvent() {}

    public RatedEvent(String eventType, String targetResource, LocalDateTime eventStartTime, int eventUnitConsumed, double totalCharge) {
        this.eventType = eventType;
        this.targetResource = targetResource;
        this.eventStartTime = eventStartTime;
        this.eventUnitConsumed = eventUnitConsumed;
        this.totalCharge = totalCharge;
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

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    @Override
    public String toString() {
        return "RatedEvent{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", targetResource='" + targetResource + '\'' +
                ", eventStartTime=" + eventStartTime +
                ", eventUnitConsumed=" + eventUnitConsumed +
                ", totalCharge=" + totalCharge +
                '}';
    }

}
