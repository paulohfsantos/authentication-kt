package com.backend.Auth.exceptions

import com.backend.Auth.dto.ErrorDTO
import com.backend.Auth.dto.ResponseDTO
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class ResponseException(
    private val status: HttpStatus,
    override val message: String
):RuntimeException(message) {
    fun handleException(): ResponseEntity<Any> {
        print("data: $message, $status")
        return ResponseEntity.status(this.status).body(
            ErrorResponse(
                message = this.message,
                status = this.status.value()
            )
        )
    }
}