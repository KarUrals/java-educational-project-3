package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private final List<Predicate<Object>> validators = new ArrayList<>();

    public final boolean isValid(Object object) {
        return validators.stream()
                .allMatch(check -> check.test(object));
    }

    protected final void addCheck(Predicate<Object> object) {
        this.validators.add(object);
    }
}
