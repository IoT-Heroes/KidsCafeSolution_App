package com.kt.gigaiot_sdk.data;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class EventApiResponse extends Response {

    private ArrayList<Event> events;
    private ArrayList<EventLog> eventLogs;

    public EventApiResponse(String responseCode, String message, ArrayList<Event> events) {
        super(responseCode, message);
        this.events = events;
    }

    public EventApiResponse(String responseCode, String message, ArrayList<Event> events, ArrayList<EventLog> eventLogs) {
        super(responseCode, message);
        this.events = events;
        this.eventLogs = eventLogs;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<EventLog> getEventLogs() {
        return eventLogs;
    }
}
