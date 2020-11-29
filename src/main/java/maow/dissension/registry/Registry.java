package maow.dissension.registry;

import maow.dissension.command.Command;
import maow.dissension.event.reaction.ReactionListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class Registry {
    public static final TypedRegistry<Command> COMMANDS = new TypedRegistry<>();
    public static final TypedRegistry<ReactionListener> REACTION_LISTENERS = new TypedRegistry<>();

    public static <T> void register(TypedRegistry<T> registry, Object id, T obj) {
        registry.register(id, obj);
    }

    public static <T> T get(TypedRegistry<T> registry, Object id) {
        return registry.get(id);
    }

    public static <T> void remove(TypedRegistry<T> registry, Object id) {
        registry.remove(id);
    }

    public static <T> Collection<T> getValues(TypedRegistry<T> registry) {
        return registry.getValues();
    }

    private static class TypedRegistry<T> {
        private final Map<Object, T> registry = new HashMap<>();

        public void register(Object id, T obj) {
            registry.put(id, obj);
        }

        public T get(Object id) {
            return registry.get(id);
        }

        public void remove(Object id) {
            registry.remove(id);
        }

        public Collection<T> getValues() {
            return registry.values();
        }
    }
}
