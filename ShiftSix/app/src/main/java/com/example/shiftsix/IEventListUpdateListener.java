package com.example.shiftsix;

import com.example.shiftsix.containers.Event;

public interface IEventListUpdateListener {
    public void addEvent(Event event);
    public void removeEvent(Event event);
}
