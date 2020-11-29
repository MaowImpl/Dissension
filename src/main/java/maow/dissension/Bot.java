package maow.dissension;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.discordjson.json.gateway.StatusUpdate;
import maow.dissension.command.Command;
import maow.dissension.registry.Registry;
import maow.dissension.util.FileIO;

import java.util.Arrays;
import java.util.Optional;

public class Bot {
    protected GatewayDiscordClient gateway;

    protected void start() {
        if (FileIO.init()) {
            System.out.println("Please specify a bot token in your 'bot-properties.json' file.");
            return;
        }
        String token = FileIO.readToken();

        final DiscordClient client = DiscordClient.create(token);
        GatewayDiscordClient gateway = client.login().block();

        if (gateway == null) return;

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            final Optional<User> author = message.getAuthor();

            if (author.isPresent() && !author.get().isBot()) {
                String content = message.getContent();
                String[] splitContent = content.split(" ");

                String commandName = splitContent[0];
                String[] commandArgs = Arrays.copyOfRange(splitContent, 1, splitContent.length);

                Command command = Registry.get(Registry.COMMANDS, commandName);
                if (command != null) {
                    command.execute(event, commandArgs);
                }
            }
        });

        gateway.onDisconnect().block();
    }
}
