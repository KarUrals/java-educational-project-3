package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> isInteger = obj -> obj instanceof Integer;
        addCheck("required", isInteger);
    }

    public NumberSchema positive() {
        Predicate<Object> positive = number -> (Integer) number > 0;
        addCheck("positive", positive);
        return this;
    }

    public NumberSchema range(int low, int high) {
        Predicate<Object> range = number -> (Integer) number >= low && (Integer) number <= high;
        addCheck("range", range);
        return this;
    }
}
