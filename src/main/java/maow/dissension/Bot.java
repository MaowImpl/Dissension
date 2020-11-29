package maow.dissension;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import maow.dissension.command.Command;
import maow.dissension.event.reaction.ReactionListener;
import maow.dissension.registry.Registry;
import maow.dissension.util.FileIO;

import java.util.Arrays;
import java.util.Collection;

public class Bot {
    private final Executor executor;

    protected Bot(Executor executor) {
        this.executor = executor;
    }

    protected void start() {
        if (FileIO.init()) {
            System.out.println("Please specify a bot token in your 'bot-properties.json' file.");
            return;
        }
        FileIO.readProperties();
        String token = FileIO.getProperty("token");

        final DiscordClient client = DiscordClient.create(token);
        GatewayDiscordClient gateway = client.login().block();

        if (gateway == null) return;

        if (executor != null) executor.execute(gateway);

        gateway.getEventDispatcher()
                .on(MessageCreateEvent.class)
                .subscribe(event -> {
                    String content = event.getMessage().getContent();
                    String[] splitContent = content.split(" ");

                    String commandName = splitContent[0];
                    String[] commandArgs = Arrays.copyOfRange(splitContent, 1, splitContent.length);

                    Command command = Registry.get(Registry.COMMANDS, commandName);
                    if (command != null) {
                        command.execute(event, commandArgs);
                    }
        });

        gateway.getEventDispatcher()
                .on(ReactionAddEvent.class)
                .subscribe(event -> {
                    final Collection<ReactionListener> listeners = Registry.getValues(Registry.REACTION_LISTENERS);
                    for (ReactionListener listener : listeners) {
                        listener.listen(event);
                    }
        });

        gateway.onDisconnect().block();
    }

    @FunctionalInterface
    public interface Executor {
        void execute(GatewayDiscordClient gateway);
    }
}
