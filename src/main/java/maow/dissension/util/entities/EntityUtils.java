package maow.dissension.util.entities;

import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.PermissionSet;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

/**
 * A collection of different utilities related to obtaining and messing with Discord4j entities.
 */
public final class EntityUtils {
    public static User getUser(Message msg) {
        if (msg == null) return null;
        Optional<User> optional = msg.getAuthor();
        return optional.orElse(null);
    }

    public static Guild getGuild(Message msg) {
        if (msg == null) return null;
        return msg.getGuild().block();
    }

    public static MessageChannel getChannel(Message msg) {
        if (msg == null) return null;
        return msg.getChannel().block();
    }

    public static Member getAsMember(Guild guild, User user) {
        if (user == null || guild == null) return null;
        Mono<Member> mono = user.asMember(guild.getId());
        return mono.block();
    }

    public static boolean checkPermissions(PermissionSet permissions, Member member) {
        if (permissions == null || member == null) return false;
        return Objects.requireNonNull(member.getBasePermissions().block()).containsAll(permissions);
    }
}
