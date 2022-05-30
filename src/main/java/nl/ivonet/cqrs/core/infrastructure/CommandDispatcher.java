package nl.ivonet.cqrs.core.infrastructure;

import nl.ivonet.cqrs.core.commands.BaseCommand;
import nl.ivonet.cqrs.core.commands.CommandHandlerMethod;

/**
 * Mediator.
 */
public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void dispatch(BaseCommand command);

}
