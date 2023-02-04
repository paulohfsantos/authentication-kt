package com.backend.Auth.controllers

import com.backend.Auth.dto.*
import com.backend.Auth.exceptions.ResponseException
import com.backend.Auth.models.User
import com.backend.Auth.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService
    private lateinit var exception: ResponseException

    @GetMapping("test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("test")
    }

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<User> {
        val user = User()
        user.username = body.username
        user.password = body.password
        user.email = body.email

        userService.save(user)

        return ResponseEntity.ok(user)
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.login(body))
        } catch (e: ResponseException) {
            e.handleException()
        }
    }
}