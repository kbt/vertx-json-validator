package net.kbt.validator

data class Message(
        val fieldName: String,
        val message: String,
        val params: Map<String, String>
) {
    constructor(message: String) : this("", message, emptyMap())

    override fun toString(): String {
        var msg = message
        params.forEach {
            k, v -> msg = msg.replace(k, v)
        }

        return "Field '$fieldName': $msg"
    }
}