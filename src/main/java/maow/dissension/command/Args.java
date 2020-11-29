package maow.dissension.command;

public class Args {
    private final Branch[] branches;

    private Args(Branch... branches) {
        this.branches = branches;
    }

    public Branch[] getBranches() {
        return branches;
    }

    public static Args start(Branch... branches) {
        return new Args(branches);
    }

    public static Branch branch(Arg... branchArgs) {
        return new Branch(false, branchArgs);
    }

    public static Branch optional(Arg... branchArgs) {
        return new Branch(true, branchArgs);
    }

    public static Arg arg(String name) {
        return new Arg(name);
    }

    public static Arg anon() {
        return arg("");
    }

    public static class Branch {
        private final boolean optional;
        private final Arg[] branchArgs;

        protected Branch(boolean optional, Arg... branchArgs) {
            this.optional = optional;
            this.branchArgs = branchArgs;
        }

        public boolean isOptional() {
            return optional;
        }

        public Arg[] getBranchArgs() {
            return branchArgs;
        }
    }

    public static class Arg {
        private final String name;

        protected Arg(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
