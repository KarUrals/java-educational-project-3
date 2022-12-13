package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    private static final String STRING_EXAMPLE = "message";
    private static final int POSITIVE_NUMBER_EXAMPLE = 2;
    private static final int NEGATIVE_NUMBER_EXAMPLE = -5;
    private static final int ZERO_NUMBER_EXAMPLE = 0;
    private static final int MIN = 6;
    private static final int MAX = 72;
    private static final int IN_RANGE_NUMBER_EXAMPLE = 54;
    private static final int OUT_RANGE_NUMBER_EXAMPLE = 4;
    private final Validator v = new Validator();
    private NumberSchema schema;

    @BeforeEach
    void beforeEach() {
        schema = v.number();
    }

    @Test
    void testDifferentInputTypesWithoutRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(POSITIVE_NUMBER_EXAMPLE));
        assertTrue(schema.isValid(STRING_EXAMPLE));
    }

    @Test
    void testDifferentInputTypesWithRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(POSITIVE_NUMBER_EXAMPLE));
        assertFalse(schema.isValid(STRING_EXAMPLE));
    }

    @Test
    void testPositiveWithoutRequired() {
        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(STRING_EXAMPLE));
        assertTrue(schema.isValid(POSITIVE_NUMBER_EXAMPLE));
        assertFalse(schema.isValid(NEGATIVE_NUMBER_EXAMPLE));
        assertFalse(schema.isValid(ZERO_NUMBER_EXAMPLE));
    }

    @Test
    void testPositiveWithRequired() {
        schema.positive().required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(STRING_EXAMPLE));
        assertTrue(schema.isValid(POSITIVE_NUMBER_EXAMPLE));
        assertFalse(schema.isValid(NEGATIVE_NUMBER_EXAMPLE));
        assertFalse(schema.isValid(ZERO_NUMBER_EXAMPLE));
    }

    @Test
    void testRangeWithoutRequired() {
        schema.range(MIN, MAX);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(STRING_EXAMPLE));
        assertTrue(schema.isValid(IN_RANGE_NUMBER_EXAMPLE));
        assertTrue(schema.isValid(MIN));
        assertTrue(schema.isValid(MAX));
        assertFalse(schema.isValid(OUT_RANGE_NUMBER_EXAMPLE));
    }

    @Test
    void testRangeWithRequired() {
        schema.range(MIN, MAX).required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(STRING_EXAMPLE));
        assertTrue(schema.isValid(IN_RANGE_NUMBER_EXAMPLE));
        assertTrue(schema.isValid(MIN));
        assertTrue(schema.isValid(MAX));
        assertFalse(schema.isValid(OUT_RANGE_NUMBER_EXAMPLE));
    }
}
