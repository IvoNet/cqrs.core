package nl.ivonet.cqrs.core.infrastructure;

import nl.ivonet.cqrs.core.events.BaseEvent;

import java.util.Iterator;

public interface EventStore {

    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);

    Iterator<BaseEvent> getEvents(String aggregateId);
}
