package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        Predicate<Object> required = map -> map instanceof Map;
        addCheck(required);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeof = map -> map instanceof Map && ((Map<?, ?>) map).size() == size;
        addCheck(sizeof);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> nestedMap) {
        Predicate<Object> shape = map -> nestedMap.entrySet().stream()
                .allMatch(entry -> entry.getValue().isValid(((Map<?, ?>) map).get(entry.getKey())));
        addCheck(shape);
        return this;
    }
}
