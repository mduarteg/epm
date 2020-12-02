package epm.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"EVENT_RATE\"")
public class EventRate {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;
    private String eventType;
    private LocalDateTime effectiveDate;
    private String uom;
    private int unitAmount;
    private int unitRate;

    public EventRate() {}

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

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public int getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(int unitAmount) {
        this.unitAmount = unitAmount;
    }

    public int getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(int unitRate) {
        this.unitRate = unitRate;
    }

    @Override
    public String toString() {
        return "EventRate{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", uom='" + uom + '\'' +
                ", unitAmount=" + unitAmount +
                ", unitRate=" + unitRate +
                '}';
    }

}
