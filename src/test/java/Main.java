import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;
import maow.dissension.Bot;
import maow.dissension.command.Args;
import maow.dissension.command.Command;
import maow.dissension.event.reaction.ReactionHandler;
import maow.dissension.event.reaction.ReactionListener;
import maow.dissension.util.text.MessageHelper;

import java.util.ArrayList;
import java.util.List;

public class Main extends Bot {
    public static void main(String[] args) {
        final Main main = new Main();
        main.run();
        main.start();
    }

    private void run() {
        Command.setGlobalPrefix("t!");
        new Command.Builder()
                .setName("potat")
                .setExecutor((event, args) -> MessageHelper.sendMessage(event.getMessage(), "Ratapotata."))
                .build();
        new Command.Builder()
                .setName("tater")
                .setExecutor((event, args) -> MessageHelper.sendMessage(event.getMessage(), "Later tater."))
                .build();

        final List<String> argsTestList = new ArrayList<>();
        final Args argsTestArgs = Args.start(
                Args.optional(),
                Args.branch(
                        Args.arg("list")
                ),
                Args.branch(
                        Args.arg("add"),
                        Args.anon()
                ),
                Args.branch(
                        Args.arg("remove"),
                        Args.anon()
                )
        );
        new Command.Builder()
                .setName("test")
                .setArgs(argsTestArgs)
                .setExecutor(((event, args) -> {
                    if (args.length > 0) {
                        switch (args[0]) {
                            case "list":
                                MessageHelper.sendMessage(event.getMessage(), argsTestList.toString());
                                break;
                            case "add":
                                argsTestList.add(args[1]);
                                break;
                            case "remove":
                                argsTestList.remove(args[1]);
                                break;
                        }
                    } else {
                        MessageHelper.sendMessage(event.getMessage(), "No args specified.");
                    }
                }))
                .build();
        new Command.Builder()
                .setName("reactiontest")
                .setExecutor((event, args) -> {
                    Message message = MessageHelper.sendMessage(event.getMessage(), "Meme.");
                    new ReactionListener(false, message,
                            new ReactionHandler(
                                ReactionEmoji.unicode("\uD83D\uDC4D"),
                                reactionEvent -> MessageHelper.sendMessage(event.getMessage(), ":)")
                            )
                    );
                })
                .build();
    }
}
