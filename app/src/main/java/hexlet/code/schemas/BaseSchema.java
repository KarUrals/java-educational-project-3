package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected final List<Predicate<Object>> validators = new ArrayList<>();

    public boolean isValid(Object object) {
        return validators.stream().allMatch(check -> check.test(object));
    }
}
