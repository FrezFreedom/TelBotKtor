package com.example.dtos

import com.example.enum.MessageFormats
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MessageDto(
    val chatId: String,
    val text: String,
    val format: String = "HTML",
)