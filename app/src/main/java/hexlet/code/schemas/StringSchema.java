package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema minLength(int length) {
        Predicate<Object> minLength = str -> ((String) str).length() >= length;
        addCheck("minLength", minLength);
        return this;
    }

    public StringSchema contains(String subStr) {
        Predicate<Object> contains = str -> ((String) str).contains(subStr);
        addCheck("contains", contains);
        return this;
    }

    public StringSchema required() {
        Predicate<Object> required = str -> str instanceof String && !(((String) str).isEmpty());
        addCheck("required", required);
        return this;
    }
}
