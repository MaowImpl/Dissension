package maow.dissension.event;

import discord4j.core.event.domain.Event;

public interface EventHandler<E extends Event> {
    Executor<E> getExecutor();

    @FunctionalInterface
    interface Executor<E> {
        void onExecute(E event);
    }
}
