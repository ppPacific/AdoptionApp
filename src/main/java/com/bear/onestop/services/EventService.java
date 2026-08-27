package com.bear.onestop.services;

import com.bear.onestop.data.CreateEventRequest;
import com.bear.onestop.data.entities.Event;


public interface EventService {

    Event createEvent(String chiefstaffId, CreateEventRequest event);
}
