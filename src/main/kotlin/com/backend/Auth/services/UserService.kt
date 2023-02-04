package com.backend.Auth.services

import com.backend.Auth.dto.LoginDTO
import com.backend.Auth.dto.ResponseDTO
import com.backend.Auth.dto.UserResponseDTO
import com.backend.Auth.exceptions.ResourceNotFoundException
import com.backend.Auth.exceptions.UserException
import com.backend.Auth.models.User
import com.backend.Auth.repositories.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    private val logger = Logger.getLogger(UserService::class.java.name)

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun save(user: User): User {
        user.encodePassword()
        return userRepository.save(user)
    }

    fun findById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { UserException("User not found") }
    }

    fun login(userDTO: LoginDTO): ResponseDTO {
        val user = userRepository.findByEmail(userDTO.email)
            .orElseThrow { UserException("User not found") }

        val token = generateToken(user.id)
        val userResponse = UserResponseDTO(user.id, user.username, user.email)

        if (!passwordEncoder.matches(userDTO.password, user.password)) {
            throw UserException("Invalid password")
        }

        return ResponseDTO(token, userResponse)
    }

    private fun generateToken(id: Long): String {
        val userId = id.toString()
        return Jwts.builder()
            .setIssuer(userId)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS256, "segredin").compact()
    }
}