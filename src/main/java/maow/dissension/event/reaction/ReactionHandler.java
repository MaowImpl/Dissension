package maow.dissension.event.reaction;

import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.reaction.ReactionEmoji;
import maow.dissension.event.EventHandler;

public class ReactionHandler implements EventHandler<ReactionAddEvent> {
    private final ReactionEmoji emoji;
    private final Executor<ReactionAddEvent> executor;

    public ReactionHandler(ReactionEmoji emoji, Executor<ReactionAddEvent> executor) {
        this.emoji = emoji;
        this.executor = executor;
    }

    public ReactionEmoji getEmoji() {
        return emoji;
    }

    @Override
    public Executor<ReactionAddEvent> getExecutor() {
        return executor;
    }
}
