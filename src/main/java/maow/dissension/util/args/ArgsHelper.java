package maow.dissension.util.args;

import maow.dissension.command.Args;
import maow.dissension.command.Command;
import maow.dissension.util.text.Format;

public final class ArgsHelper {
    public static ParseResult parse(Command command, String[] userArgs) {
        ParseResult result = new ParseResult(true, null);
        if (command.getArgs() != null) {
            final Args.Branch[] branches = command.getArgs().getBranches();
            for (Args.Branch branch : branches) {
                if (branch.isOptional() && userArgs.length == 0) {
                    result = new ParseResult(true, branch);
                    break;
                }
                final Args.Arg[] branchArgs = branch.getBranchArgs();
                if (branchArgs.length == userArgs.length) {
                    if (!userArgs[0].equals(branchArgs[0].getName())) continue;
                    for (int i = 1; i < branchArgs.length; i++) {
                        final Args.Arg arg = branchArgs[i];
                        if (!userArgs[i].equals(arg.getName()) && !arg.getName().equals(""))
                            return new ParseResult(false, branch);
                    }
                    result = new ParseResult(true, branch);
                    break;
                } else {
                    result = new ParseResult(false, branch);
                    if (branchArgs.length != 0 && userArgs.length != 0) {
                        if (userArgs[0].equals(branchArgs[0].getName())) break;
                    }
                }
            }
        }
        return result;
    }

    public static String getUsage(Command command, Args.Branch branch) {
        final StringBuilder builder = new StringBuilder(Format.bold("Incorrect syntax."));
        builder
                .append("\nExample: ")
                .append(command.getPrefix())
                .append(command.getName())
                .append(" ");
        for (Args.Arg arg : branch.getBranchArgs()) {
            final String name = (arg.getName().equals("")) ? "<user input>" : arg.getName();
            builder
                    .append(name)
                    .append(" ");
        }
        return builder.toString();
    }
}
