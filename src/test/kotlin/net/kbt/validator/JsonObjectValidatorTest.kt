package net.kbt.validator

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.junit.Assert
import org.junit.Test

class JsonObjectValidatorTest {
    @Test
    fun validateObject() {
        val jsonString = "{\n  \"a\": 4,\n  \"b\": \"g\",\n  \"c\": {\n    \"dd\": \"12\",\n    \"ee\": \"12\"\n  }\n}"
        val json = JsonObject(jsonString)

        val validator = JsonObjectValidator()
                .add("a", IntegerValidator())
                .add("b", IntegerValidator())
                .addRequired("x", IntegerValidator())
                .add("c", JsonObjectValidator()
                        .add("dd", IntegerValidator())
                        .add("ee", UuidValidator())
                )

        val msgs = validator.validate(json)
                .messages
                .map { msg -> msg.toString() }

        val expected = listOf(
                "Field 'b': not an integer",
                "Field 'x': required field not found",
                "Field 'c.dd': not an integer",
                "Field 'c.ee': not an uuid"
        )

        Assert.assertEquals(expected, msgs)
    }

    @Test
    fun validateArray() {
        val jsonString = "[\n  {\n    \"a\": 1,\n    \"c\": [\n      1\n    ]\n  },\n  {\n    \"a\": 2,\n    \"c\": [\"err\"]\n  }\n]\n"
        val json = JsonArray(jsonString)

        val validator = JsonArrayValidator(
                JsonObjectValidator()
                        .add("a", IntegerValidator())
                        .add("c",
                                JsonArrayValidator(IntegerValidator())
                        )
        )

        val msgs = validator.validate(json)
                .messages
                .map { msg -> msg.toString() }

        val expected = listOf(
                "Field '1.c.0': not an integer"
        )

        Assert.assertEquals(expected, msgs)
    }

    @Test
    fun validateArrayNotExpected() {
        val jsonString = "{\n  \"a\": 1,\n  \"c\": [\n    1\n  ]\n}\n"
        val json = JsonObject(jsonString)

        val validator = JsonObjectValidator()
                .add("c", JsonObjectValidator())

        val msgs = validator.validate(json)
                .messages
                .map { msg -> msg.toString() }

        val expected = listOf(
                "Field 'c': array not expected"
        )

        Assert.assertEquals(expected, msgs)
    }
}
