package epm.event;

import epm.util.EventType;

import java.time.LocalDateTime;
import java.util.Date;

public class GPRSEvent extends BaseEvent {
    private String targetResource;
    private String website;
    private Integer dataConsumed;

    public GPRSEvent(EventType eventType, boolean isRejected, double totalCharge, LocalDateTime startTime, String targetResource, String website, Integer dataConsumed, String rejectionReason) {
        super(eventType, isRejected, totalCharge, startTime, rejectionReason);
        this.targetResource = targetResource;
        this.website = website;
        this.dataConsumed = dataConsumed;
    }

    public String getTargetResource() {
        return targetResource;
    }

    public void setTargetResource(String targetResource) {
        this.targetResource = targetResource;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getDataConsumed() {
        return dataConsumed;
    }

    public void setDataConsumed(Integer dataConsumed) {
        this.dataConsumed = dataConsumed;
    }

    @Override
    public String toString() {
        return "GPRSEvent{" +
                "targetResource='" + targetResource + '\'' +
                ", website='" + website + '\'' +
                ", dataConsumed=" + dataConsumed +
                " " + super.toString() +
                '}';
    }
}