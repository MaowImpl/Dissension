package maow.dissension.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.rest.util.Permission;
import discord4j.rest.util.PermissionSet;
import maow.dissension.util.args.ArgsHelper;
import maow.dissension.registry.Registry;
import maow.dissension.util.text.MessageHelper;
import maow.dissension.util.args.ParseResult;

import static maow.dissension.util.entities.EntityUtils.*;

public class Command {
    private static String globalPrefix;

    private final String prefix;
    private final String name;
    private final String description;
    private final PermissionSet permissions;
    private final Args args;
    private final Executor executor;

    private Command(String prefix, String name, String description, PermissionSet permissions, Args args, Executor executor) {
        this.prefix = prefix;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.args = args;
        this.executor = executor;
        Registry.register(Registry.COMMANDS, prefix + name, this);
    }

    public static void setGlobalPrefix(String globalPrefix) {
        Command.globalPrefix = globalPrefix;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public PermissionSet getPermissions() {
        return permissions;
    }

    public Args getArgs() {
        return args;
    }

    public String getPrefix() {
        return prefix;
    }

    public void execute(MessageCreateEvent event, String[] args) {
        final Message message = event.getMessage();
        final Member author = getAsMember(getGuild(message), getUser(message));
        if (author != null && checkPermissions(permissions, author)) {
            final ParseResult result = ArgsHelper.parse(this, args);
            if (!result.isSuccessful()) {
                MessageHelper.sendMessage(message, ArgsHelper.getUsage(this, result.getBranch()));
                return;
            }
            executor.onExecute(event, args);
        }
    }

    public static final class Builder {
        private String prefix = globalPrefix;
        private String name;
        private String description;
        private PermissionSet permissions = PermissionSet.none();
        private Args args;
        private Executor executor;

        public Builder setPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder addPermissions(Permission... permissions) {
            this.permissions = PermissionSet.of(permissions);
            return this;
        }

        public Builder setArgs(Args args) {
            this.args = args;
            return this;
        }

        public Builder setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        @SuppressWarnings("UnusedReturnValue")
        public Command build() {
            return new Command(prefix, name, description, permissions, args, executor);
        }
    }

    @FunctionalInterface
    public interface Executor {
        void onExecute(MessageCreateEvent event, String[] args);
    }
}
