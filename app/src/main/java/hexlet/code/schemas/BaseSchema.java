package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {

    private final Map<String, Predicate<Object>> validators = new HashMap<>();
    public final boolean isValid(Object object) {
        return validators.values().stream()
                .allMatch(check -> check.test(object));
    }
    protected final void addCheck(String name, Predicate<Object> object) {
        this.validators.put(name, object);
    }
}
