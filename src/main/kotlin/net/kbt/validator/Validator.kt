package net.kbt.validator

import io.vertx.core.json.JsonObject

interface Validator {
    fun validate(obj: Any): List<Message>
}