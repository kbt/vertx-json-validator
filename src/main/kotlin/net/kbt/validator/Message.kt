package net.kbt.validator

data class FieldMessage(
        val fieldName: String,
        val message: Message
)

data class Message(
        val message: String,
        val params: Map<String, String>
) {
    constructor(message: String) : this(message, emptyMap())
}