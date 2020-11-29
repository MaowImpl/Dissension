package maow.dissension.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileIO {
    private static final Path BOT_PROPERTIES_PATH = Paths.get("bot-properties.json");
    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    public static boolean init() {
        if (Files.notExists(BOT_PROPERTIES_PATH)) {
            final Map<String, String> map = new HashMap<>();
            map.put("token", "");
            try {
                final FileWriter writer = new FileWriter(BOT_PROPERTIES_PATH.toFile());
                GSON.toJson(map, writer);
                writer.flush();
                writer.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static String readToken() {
        Map<String, String> map = new HashMap<>();
        try {
            final FileReader reader = new FileReader(BOT_PROPERTIES_PATH.toFile());
            map = GSON.fromJson(reader, HashMap.class);
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return map.get("token");
    }
}
