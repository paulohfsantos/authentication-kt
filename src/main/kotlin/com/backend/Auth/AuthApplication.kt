package com.backend.Auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class AuthApplication

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
	println("restarting app...")
}
