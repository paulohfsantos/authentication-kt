package com.backend.Auth.helpers

class SuccessMessage {
    fun get(message: String): Map<String, String> {
        return mapOf("message" to message)
    }
}