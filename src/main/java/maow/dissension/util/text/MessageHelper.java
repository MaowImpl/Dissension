package maow.dissension.util.text;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import maow.dissension.util.entities.EntityUtils;

public final class MessageHelper {
    public static Message sendMessage(Message message, String text) {
        MessageChannel channel = EntityUtils.getChannel(message);
        return channel.createMessage(text).block();
    }

    public static Message sendMessage(MessageChannel channel, String text) {
        return channel.createMessage(text).block();
    }

    public static Message sendEmbed(Message message, EmbedCreateSpec embed) {
        MessageChannel channel = EntityUtils.getChannel(message);
        return channel.createEmbed(embedCreateSpec -> embed.asRequest()).block();
    }

    public static Message sendEmbed(MessageChannel channel, EmbedCreateSpec embed) {
        return channel.createEmbed(embedCreateSpec -> embed.asRequest()).block();
    }
}
