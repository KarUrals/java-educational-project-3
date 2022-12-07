package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        Predicate<Object> required = number -> number instanceof Integer;
        addCheck(required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positive = number -> number instanceof Integer && (Integer) number > 0 || number == null;
        addCheck(positive);
        return this;
    }

    public NumberSchema range(int low, int high) {
        Predicate<Object> range = number -> (Integer) number >= low && (Integer) number <= high;
        addCheck(range);
        return this;
    }
}
