package nl.ivonet.cqrs.core.domain;

import lombok.Getter;
import lombok.Setter;
import nl.ivonet.cqrs.core.events.BaseEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AggregateRoot {
    private static final Logger log = Logger.getLogger(AggregateRoot.class.getName());
    private final List<BaseEvent> changes = new ArrayList<>();
    @Getter
    protected String id;
    @Getter
    @Setter
    private int version = -1;

    public Iterator<BaseEvent> getUncommittedChanges() {
        return changes.iterator();
    }

    public void markChangesAsCommitted() {
        changes.clear();
    }

    protected void applyChange(BaseEvent event, Boolean isNew) {
        try {
            var method = this.getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            log.warning("No apply method found for event " + event.getClass().getName());
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.log(Level.SEVERE, "Error while applying event " + event.getClass().getName(), e);
        } finally {
            if (isNew) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }
}
