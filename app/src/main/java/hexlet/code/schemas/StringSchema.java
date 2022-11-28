package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {

    private final List<Predicate<String>> validators = new ArrayList<>();

    public boolean isValid(Object object) {
        if (object != null && !(object instanceof String))  {
            return false;
        }
        return validators.stream().allMatch(check -> check.test((String) object));
    }


    public StringSchema minLength(int length) {
        Predicate<String> minLength = str -> str.length() >= length;
        validators.add(minLength);
        return this;
    }

    public StringSchema contains(String subStr) {
        Predicate<String> contains = str -> str.contains(subStr);
        validators.add(contains);
        return this;
    }

    public void required() {
        Predicate<String> required = str -> str != null && !str.trim().equals("");
        validators.add(required);
    }
}
