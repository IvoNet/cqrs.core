package nl.ivonet.cqrs.core.producers;

import nl.ivonet.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void publish(String topic, BaseEvent event);
}
