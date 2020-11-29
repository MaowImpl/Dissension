package maow.dissension.util.args;

import maow.dissension.command.Args;

public final class ParseResult {
    private final boolean success;
    private final Args.Branch branch;

    public ParseResult(boolean success, Args.Branch branch) {
        this.success = success;
        this.branch = branch;
    }

    public boolean isSuccessful() {
        return success;
    }

    public Args.Branch getBranch() {
        return branch;
    }
}
