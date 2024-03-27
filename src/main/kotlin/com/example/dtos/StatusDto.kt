package com.example.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class StatusDto(
    val ok: Boolean,
)