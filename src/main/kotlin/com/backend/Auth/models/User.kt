package com.backend.Auth.models

import com.backend.Auth.dto.UserResponseDTO
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "users_auth")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "username", nullable = false)
    var username: String = "",

    @Column(name = "password", nullable = false)
    var password: String = "",

    @Column(name = "email", unique = true, nullable = false)
    var email: String = ""
) {
    fun encodePassword() {
        val encoder = BCryptPasswordEncoder()
        this.password = encoder.encode(this.password)
    }

    fun convertToDTO(): UserResponseDTO {
        return UserResponseDTO(
            id = this.id,
            username = this.username,
            email = this.email
        )
    }
}