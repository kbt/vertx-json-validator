package net.kbt.validator

class IntegerValidator : Validator {
    companion object {
        private val MSG_NOT_AN_INTEGER = "not an integer"
    }

    override fun validate(obj: Any): List<Message> {
        val messages = mutableListOf<Message>()
        try {
            obj as Number
        } catch (e: Exception) {
            messages.add(Message(MSG_NOT_AN_INTEGER))
        }

        return messages
    }
}
