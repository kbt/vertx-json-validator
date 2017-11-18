package net.kbt.validator

import io.vertx.core.json.JsonObject
import org.junit.Test

class JsonObjectValidatorTest {
    @Test
    fun validate() {
        val jsonString = "{\n  \"a\": 4,\n  \"b\": \"g\"\n}"
        val json = JsonObject(jsonString)

        val validator = JsonObjectValidator()

        validator.addRequired("a", IntegerValidator())

        val msgs = validator.validate(json).getMessages()

        msgs.forEach { msg -> println("'" + msg.fieldName + "' " + msg.message.message + " " + msg.message.params) }
    }
}