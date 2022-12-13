package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    private static final String EMPTY_STRING_EXAMPLE = "";
    private static final String STRING_1_EXAMPLE = "random string";
    private static final String STRING_2_EXAMPLE = "extended random string";
    private static final String SUB_STRING_EXAMPLE = "random";
    private static final int NUMBER_EXAMPLE = 47;
    private static final int STRING_LENGTH = 13;
    private static final String SHORTER_STRING_EXAMPLE = "java";

    private final Validator v = new Validator();
    private StringSchema schema;

    @BeforeEach
    void beforeEach() {
        schema = v.string();
    }

    @Test
    void testDifferentInputTypesWithoutRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(NUMBER_EXAMPLE));
        assertTrue(schema.isValid(EMPTY_STRING_EXAMPLE));
        assertTrue(schema.isValid(STRING_1_EXAMPLE));
    }

    @Test
    void testDifferentInputTypesWithRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(EMPTY_STRING_EXAMPLE));
        assertTrue(schema.isValid(STRING_1_EXAMPLE));
    }

    @Test
    void testMinLengthWithoutRequired() {
        schema.minLength(STRING_LENGTH);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(EMPTY_STRING_EXAMPLE));
        assertTrue(schema.isValid(STRING_1_EXAMPLE));
        assertFalse(schema.isValid(SHORTER_STRING_EXAMPLE));
    }

    @Test
    void testMinLengthWithRequired() {
        schema.minLength(STRING_LENGTH).required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(EMPTY_STRING_EXAMPLE));
        assertTrue(schema.isValid(STRING_1_EXAMPLE));
        assertFalse(schema.isValid(SHORTER_STRING_EXAMPLE));
    }

    @Test
    void testContainsWithoutRequired() {
        schema.contains(SUB_STRING_EXAMPLE);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(EMPTY_STRING_EXAMPLE));
        assertTrue(schema.isValid(STRING_1_EXAMPLE));
        assertFalse(schema.isValid(SHORTER_STRING_EXAMPLE));
    }

    @Test
    void testContainsWithRequired() {
        schema.contains(SUB_STRING_EXAMPLE).required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(EMPTY_STRING_EXAMPLE));
        assertTrue(schema.isValid(STRING_1_EXAMPLE));
        assertFalse(schema.isValid(SHORTER_STRING_EXAMPLE));
    }

    @Test
    void testCombinationChecks() {
        schema.contains(SUB_STRING_EXAMPLE).required().minLength(STRING_LENGTH + 1);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(EMPTY_STRING_EXAMPLE));
        assertTrue(schema.isValid(STRING_2_EXAMPLE));
        assertFalse(schema.isValid(STRING_1_EXAMPLE));
    }
}
