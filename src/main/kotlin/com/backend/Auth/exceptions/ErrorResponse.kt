package com.backend.Auth.exceptions

import org.springframework.http.ResponseEntity

class ErrorResponse(
    val message: String,
    private val status: Int
) {
    fun resError(): ResponseEntity<Any> {
        return ResponseEntity.status(this.status).body(this)
    }
}