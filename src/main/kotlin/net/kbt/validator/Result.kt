package net.kbt.validator

class Result {
    private val messages = ArrayList<FieldMessage>()

    fun add(fieldName: String, msgs: List<Message>) {
        msgs.forEach {
            message -> messages.add(FieldMessage(fieldName, message))
        }
    }

    fun getMessages() = messages
}
