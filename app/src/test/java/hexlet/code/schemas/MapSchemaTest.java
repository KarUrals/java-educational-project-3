package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    private static final String STRING_EXAMPLE = "java";
    private static final int NUMBER_EXAMPLE = 96;
    private static final int MAP_SIZE_2 = 2;
    private final Validator v = new Validator();
    private MapSchema schema;

    @BeforeEach
    void prepareTests() {
        schema = v.map();
    }

    @Test
    void testDifferentInputTypesWithoutRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(NUMBER_EXAMPLE));
        assertTrue(schema.isValid(STRING_EXAMPLE));
        assertTrue(schema.isValid(new HashMap()));
    }

    @Test
    void testDifferentInputTypesWithRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(STRING_EXAMPLE));
        assertTrue(schema.isValid(new HashMap()));
    }

    @Test
    void testSizeOfWithoutRequired() {
        schema.sizeof(MAP_SIZE_2);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(NUMBER_EXAMPLE));
        assertTrue(schema.isValid(STRING_EXAMPLE));
    }

    @Test
    void testSizeOfWithRequired() {
        schema.sizeof(MAP_SIZE_2).required();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(NUMBER_EXAMPLE));
        assertFalse(schema.isValid(STRING_EXAMPLE));
    }

    @Test
    void mapSchemaNestedTest() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));
    }
}
