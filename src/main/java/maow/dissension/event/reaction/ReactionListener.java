package maow.dissension.event.reaction;

import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.entity.Message;
import maow.dissension.event.EventListener;
import maow.dissension.registry.Registry;

public class ReactionListener implements EventListener<ReactionAddEvent> {
    private final boolean killAfterExecution;
    private final Message message;
    private final ReactionHandler[] handlers;

    public ReactionListener(boolean killAfterExecution, Message message, ReactionHandler... handlers) {
        this.killAfterExecution = killAfterExecution;
        this.message = message;
        this.handlers = handlers;
        Registry.register(Registry.REACTION_LISTENERS, this, this);
    }

    @Override
    public ReactionHandler[] getHandlers() {
        return handlers;
    }

    @Override
    public void listen(ReactionAddEvent event) {
        if (killAfterExecution) {
            Registry.remove(Registry.REACTION_LISTENERS, this);
        }
        final Message message = event.getMessage().block();
        if (message != null && this.message.getId().equals(message.getId())) {
            for (ReactionHandler handler : handlers) {
                if (handler.getEmoji().equals(event.getEmoji())) {
                    handler.getExecutor().onExecute(event);
                }
            }
        }
    }
}
