package maow.dissension.util.text;

public final class Format {
    public static String bold(String text) {
        return "**" + text + "**";
    }

    public static String italics(String text) {
        return "*" + text + "*";
    }

    public static String codeblock(String text, String style) {
        return "```" + style + "\n" + text + "\n```";
    }

    public static String codeblock(String text) {
        return codeblock(text, "");
    }

    public static String codeline(String text) {
        return "`" + text + "`";
    }

    public static String quote(String text) {
        final StringBuilder sb = new StringBuilder();
        final String[] lines = text.split("\n");
        for (String line : lines) {
            sb
                    .append("> ")
                    .append(line);
        }
        return sb.toString();
    }

    public static String spoiler(String text) {
        return "||" + text + "||";
    }

    public static String underline(String text) {
        return "__" + text + "__";
    }

    public static String strikethrough(String text) {
        return "~~" + text + "~~";
    }
}
