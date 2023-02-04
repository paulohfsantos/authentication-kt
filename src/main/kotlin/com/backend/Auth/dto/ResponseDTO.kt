package com.backend.Auth.dto

import com.backend.Auth.models.User

class ResponseDTO (
    val token: String,
    val user: UserResponseDTO
)