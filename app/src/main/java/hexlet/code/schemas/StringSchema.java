package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        Predicate<Object> isString = obj -> obj instanceof String;
        addCheck("string", isString);
    }

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

    @Override
    public StringSchema required() {
        super.required();
        Predicate<Object> isNotEmpty = str -> !(((String) str).isEmpty());
        addCheck("notEmpty", isNotEmpty);
        return this;
    }
}
