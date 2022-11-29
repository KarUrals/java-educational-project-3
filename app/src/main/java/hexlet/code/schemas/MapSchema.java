package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

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

    public MapSchema shape(Map<String, BaseSchema> nestedMap) {

//        for (Map.Entry<String, BaseSchema> entry : nestedMap.entrySet()) {
//            String key = entry.getKey();
//            BaseSchema schema = entry.getValue();
////            Predicate<Object> p = v -> ((Map<?, ?>) v).containsKey(key);
////            validators.add(p);
//            Predicate<Object> p1 = map -> schema.isValid( ((Map<?, ?>) map).get(key));
//            validators.add(p1);
//        }

        Predicate<Object> shape = map -> nestedMap.entrySet().stream()
                .allMatch(entry -> entry.getValue().isValid(((Map<?, ?>) map).get(entry.getKey())));
        validators.add(shape);
        return this;
    }
}
