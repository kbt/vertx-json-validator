package net.kbt.validator

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject

class JsonObjectValidator : Validator {
    companion object {
        private val MSG_REQUIRED = "required field not found"
        private val MSG_ARRAY_NOT_EXPECTED = "array not expected"
    }

    private val validators = mutableMapOf<String, Validator>()
    private val required = mutableSetOf<String>()

    override fun validate(obj: Any): Result {
        val result = Result()

        if (obj is JsonArray) {
            result.add(Message(MSG_ARRAY_NOT_EXPECTED))
        } else {
            validators.forEach { fieldName, validator ->

                val target: Any? = (obj as JsonObject).getValue(fieldName)

                if (target == null) {
                    if (required.contains(fieldName)) {
                        result.addPrefixed(fieldName, Message(MSG_REQUIRED))
                    }
                } else {
                    result.addPrefixed(fieldName, validator.validate(target).messages)
                }
            }
        }

        return result
    }

    fun add(fieldName: String, validator: Validator): JsonObjectValidator {
        validators.put(fieldName, validator)

        return this
    }

    fun addRequired(fieldName: String, validator: Validator): JsonObjectValidator {
        add(fieldName, validator)
        required.add(fieldName)

        return this
    }
}
