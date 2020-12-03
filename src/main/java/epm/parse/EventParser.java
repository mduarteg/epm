package epm.parse;

import epm.event.BaseEvent;
import epm.event.GPRSEvent;
import epm.event.GSMEvent;
import epm.event.SMSEvent;
import epm.exception.EventFormatException;
import epm.util.EventType;
import epm.util.QueueManager;
import epm.util.TypeUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class EventParser implements Runnable {

    private static final Logger logger = Logger.getLogger(EventParser.class);

    public void parseEvent() {
        String line;

        try {
            line = QueueManager.parseQueue.poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return;
        }

        BaseEvent event;

        if (line == null) return;

        event = stringToEvent(line);

        if (event == null) return;

        try {
            QueueManager.rateQueue.put(event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private BaseEvent stringToEvent(String line) {
        String[] csv = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try {
            validateFormat(line);
        } catch (EventFormatException e) {
            logger.error(String.format("Event line { %s } was rejected with error: %s", line, e.getMessage()));
            return rejectEventLine(e.getMessage());
        }

        switch (EventType.valueOf(csv[0])) {
            case GSM:
                return new GSMEvent(
                        EventType.valueOf(csv[0]),
                        false,
                        0.0,
                        LocalDateTime.parse(csv[2], formatter),
                        csv[1],
                        csv[4],
                        Integer.parseInt(csv[3]),
                        null);
            case GPRS:
                return new GPRSEvent(
                        EventType.valueOf(csv[0]),
                        false,
                        0.0,
                        LocalDateTime.parse(csv[2], formatter),
                        csv[1],
                        csv[4],
                        Integer.parseInt(csv[3]),
                        null);
            case SMS:
                return new SMSEvent(
                        EventType.valueOf(csv[0]),
                        false,
                        0.0,
                        LocalDateTime.parse(csv[2], formatter),
                        csv[1],
                        csv[4],
                        Integer.parseInt(csv[3]),
                        null);
            default:
                return null;
        }
    }

    private BaseEvent rejectEventLine(String message) {
        return new BaseEvent(
                EventType.REJECTED,
                true,
                0.0,
                LocalDateTime.now(),
                message
        );
    }

    private void validateFormat(String line) throws EventFormatException {
        String[] csv = line.split(",");

        validateLength(csv);
        validateData(csv);

    }

    private void validateData(String[] csv) throws EventFormatException {
        if (TypeUtils.isNullOrEmpty(csv[1]) || TypeUtils.isNullOrEmpty(csv[4])) {
            throw new EventFormatException("Event could not be parsed - Resources are missing");
        }

        if (!TypeUtils.isEnumValue(csv[0])) {
            throw new EventFormatException("Event could not be parsed - Event type is invalid");
        }

        if (!TypeUtils.isInteger(csv[3])) {
            throw new EventFormatException("Event could not be parsed - Event consumption is invalid");
        }

        if (!TypeUtils.isLocalDateTime(csv[2])) {
            throw new EventFormatException("Event could not be parsed - Event date is invalid");
        }
    }

    private void validateLength(String[] csv) throws EventFormatException {
        if (csv.length != 5) {
            throw new EventFormatException("Event could not be parsed - The number of values is invalid");
        }
    }


    @Override
    public void run() {
        while(true) {
            parseEvent();
        }
    }

}
