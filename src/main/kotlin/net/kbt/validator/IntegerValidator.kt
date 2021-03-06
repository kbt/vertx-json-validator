package net.kbt.validator

class IntegerValidator : Validator {
    companion object {
        private val MSG_NOT_AN_INTEGER = "not an integer"
    }

    override fun validate(obj: Any): Result {
        val result = Result()
        try {
            obj as Number
        } catch (e: Exception) {
            result.add(Message(MSG_NOT_AN_INTEGER))
        }

        return result
    }
}
