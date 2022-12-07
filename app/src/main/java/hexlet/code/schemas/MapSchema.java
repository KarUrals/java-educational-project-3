package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> isMap = obj -> obj instanceof Map;
        addCheck("map", isMap);
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeof = map -> ((Map<?, ?>) map).size() == size;
        addCheck("sizeof", sizeof);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> nestedMap) {
        Predicate<Object> shape = map -> nestedMap.entrySet().stream()
                .allMatch(entry -> entry.getValue().isValid(((Map<?, ?>) map).get(entry.getKey())));
        addCheck("shape", shape);
        return this;
    }
}
