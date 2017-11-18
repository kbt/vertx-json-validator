package net.kbt.validator

class Result {
    companion object {
        const val SEPARATOR = "."
    }

    val messages = ArrayList<Message>()

    fun addPrefixed(fieldName: String, msgs: List<Message>) {
        msgs.forEach { message ->
            val sep = if (fieldName == "" || message.fieldName == "") "" else SEPARATOR

            messages.add(Message(
                    fieldName + sep + message.fieldName,
                    message.message,
                    message.params
            ))
        }
    }

    fun add(msgs: List<Message>) {
        addPrefixed("", msgs)
    }

    fun add(msg: Message) {
        addPrefixed("", listOf(msg))
    }

    fun addPrefixed(fieldName: String, msg: Message) {
        addPrefixed(fieldName, listOf(msg))
    }
}
