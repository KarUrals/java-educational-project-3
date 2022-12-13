package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private boolean isRequired = false;
    private final Map<String, Predicate<Object>> validators = new HashMap<>();

    public final boolean isValid(Object object) {
        Predicate<Object> requiredCheck = validators.get("required");
        if (!isRequired) {
            if (!requiredCheck.test(object)) {
                return true;
            }
        }

        if (object == null) {
            return !isRequired;
        }

        if (!requiredCheck.test(object)) {
            return false;
        }

        return validators.values().stream()
                .allMatch(check -> check.test(object));
    }

    /**
     * Add validation that object not Null.
     *
     * @return this Schema
     */
    public BaseSchema required() {
        isRequired = true;
        return this;
    }

    protected final void addCheck(String name, Predicate<Object> object) {
        this.validators.put(name, object);
    }
}
