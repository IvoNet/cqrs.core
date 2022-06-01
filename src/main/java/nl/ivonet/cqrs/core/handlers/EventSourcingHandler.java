package nl.ivonet.cqrs.core.handlers;

import nl.ivonet.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);

    T getById(String id);

    void republishEvents();
}
