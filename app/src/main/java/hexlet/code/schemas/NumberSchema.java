package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        Predicate<Object> required = number -> number instanceof Integer;
        validators.add(required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positive = number -> number instanceof Integer && (Integer) number > 0 || number == null;
        validators.add(positive);
        return this;
    }

    public NumberSchema range(int low, int high) {
        Predicate<Object> range = number -> (Integer) number >= low && (Integer) number <= high;
        validators.add(range);
        return this;
    }
}
