package maow.dissension.event;

import discord4j.core.event.domain.Event;

public interface EventListener<E extends Event> {
    EventHandler<E>[] getHandlers();
    void listen(E event);
}
