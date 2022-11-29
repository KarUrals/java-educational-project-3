package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema{

    public MapSchema required() {
        Predicate<Object> required = map -> map instanceof Map;
        validators.add(required);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeof = map -> map instanceof Map && ((Map<?, ?>) map).size() == size;
        validators.add(sizeof);
        return this;
    }
}
