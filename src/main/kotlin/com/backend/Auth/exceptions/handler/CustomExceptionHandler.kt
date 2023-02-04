package com.backend.Auth.exceptions.handler

import com.backend.Auth.exceptions.ExceptionResponse
import com.backend.Auth.exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Date

@ControllerAdvice
@RestController
class CustomExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, req: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            timestamp = Date(),
            message = ex.message ?: "Unknown error",
            details = req.getDescription(false)
        )

        return ResponseEntity<ExceptionResponse>(
            exceptionResponse,
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        ex: Exception,
        req: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            timestamp = Date(),
            message = ex.message ?: "Unknown error",
            details = req.getDescription(false)
        )

        return ResponseEntity<ExceptionResponse>(
            exceptionResponse,
            HttpStatus.NOT_FOUND
        )
    }
}