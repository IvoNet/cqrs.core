package nl.ivonet.cqrs.core.infrastructure;

import nl.ivonet.cqrs.core.domain.BaseEntity;
import nl.ivonet.cqrs.core.queries.BaseQuery;
import nl.ivonet.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

/**
 * (Abstract) Mediator for handling queries.
 */
public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

    <U extends BaseEntity> List<U> dispatch(BaseQuery query);
}
