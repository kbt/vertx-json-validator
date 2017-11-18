package net.kbt.validator

import io.vertx.core.json.JsonObject

class JsonObjectValidator {
    companion object {
        private val MSG_REQUIRED = "required field not found"
    }

    private val validators = mutableMapOf<String, Validator>()
    private val required = mutableSetOf<String>()

    fun validate(obj: Any): Result {
        val result = Result()

        validators.forEach { fieldName, validator ->
            val target: Any? = (obj as JsonObject).getValue(fieldName)

            if (target == null) {
                if (required.contains(fieldName)) {
                    result.add(fieldName, listOf(Message(MSG_REQUIRED)))
                }
            } else {
                result.add(fieldName, validator.validate(target))
            }
        }

        return result
    }

    fun add(fieldName: String, validator: Validator) {
        validators.put(fieldName, validator)
    }

    fun addRequired(fieldName: String, validator: Validator) {
        add(fieldName, validator)
        required.add(fieldName)
    }
}