# Data validator
This project is a library that can be used for check the correctness (validation) of data. Library allows the following checks:
+ **String**
    + *required* - any non-empty string
    + *minLength* - the string is equal to or longer than the specified number
    + *contains* - the string contains a specific substring
+ **Number**
    + *required* - any number including zero
    + *positive* - positive number
    + *range* - the range in which the number should fall, including borders
+ **Map** *(nested validation support)*
    + *required* - the Map data type is required
    + *sizeof* - the number of key-value pairs in the Map must be equal to the specified

___

## Service badges

| Description | Badge |
|:---|:---|
|Hexlet tests and linter status:|[![Actions Status](https://github.com/KarUrals/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/KarUrals/java-project-78/actions)|
|GitHub Actions workflow status:|[![Java CI](https://github.com/KarUrals/java-project-78/actions/workflows/test-and-linter-check.yml/badge.svg)](https://github.com/KarUrals/java-project-78/actions/workflows/test-and-linter-check.yml)|
|CodeClimate maintainability status:|[![Maintainability](https://api.codeclimate.com/v1/badges/62e9408a2582cf608b5e/maintainability)](https://codeclimate.com/github/KarUrals/java-project-78/maintainability)|
|CodeClimate TestCoverage status:|[![Test Coverage](https://api.codeclimate.com/v1/badges/62e9408a2582cf608b5e/test_coverage)](https://codeclimate.com/github/KarUrals/java-project-78/test_coverage)|

___

## Usage example

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// Strings
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// Numbers
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// Map object with structure checking support
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```

___

## Frequently used commands

#### Install
```
make install
```

#### Run
```
make run-dist
```

#### Build
```
make build
```

#### Run checkstyle
```
make lint
```

#### Check for Dependency Updates
```
make check-updates
```