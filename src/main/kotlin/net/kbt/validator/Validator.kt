package net.kbt.validator

interface Validator {
    fun validate(obj: Any): Result
}