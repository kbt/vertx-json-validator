package net.kbt.validator

import java.util.*

class UuidValidator : Validator {
    companion object {
        private val MSG_NOT_AN_UUID = "not an uuid"
    }

    override fun validate(obj: Any): Result {
        val result = Result()
        try {
            UUID.fromString(obj as String)
        } catch (e: Exception) {
            result.add(Message(MSG_NOT_AN_UUID))
        }

        return result
    }
}
