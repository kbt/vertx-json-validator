package net.kbt.validator

import io.vertx.core.json.JsonArray

class JsonArrayValidator(private val validator: Validator) : Validator {
    override fun validate(obj: Any): Result {
        val result = Result()

        if (obj is JsonArray) {
            var cnt = 0
            obj.forEach { item ->
                result.addPrefixed((cnt++).toString(), validator.validate(item).messages)
            }
        } else {
            //TODO
        }

        return result
    }
}
