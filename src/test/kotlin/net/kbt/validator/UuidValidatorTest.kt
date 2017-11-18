package net.kbt.validator

import org.junit.Assert

import org.junit.Test
import java.util.*

class UuidValidatorTest {
    @Test
    fun notAnUuid() {
        val messages = UuidValidator()
                .validate("123")
                .messages
                .map { msg -> msg.toString() }

        val expected = listOf(
                "Field '': not an uuid"
        )

        Assert.assertEquals(expected, messages)
    }

    @Test
    fun validUuid() {
        val messages = UuidValidator()
                .validate(UUID.randomUUID().toString())
                .messages
                .map { msg -> msg.toString() }

        Assert.assertEquals(emptyList<String>(), messages)
    }
}