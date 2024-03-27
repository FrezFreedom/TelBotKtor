package com.example.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class UpdateDto (
    val ok: Boolean? = null,
    val result: List<Update?>? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Update(
    val update_id: Long? = null,
    val message: Message? = null,
    val my_chat_member: Message? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Message(
    val message_id: Long? = null,
    val from: From? = null,
    val chat: Chat? = null,
    val date: Long? = null,
    val text: String? = null,
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class From(
    val id: Long? = null,
    val is_bot: Boolean? = null,
    val first_name: String? = null,
    val username: String? = null,
    val language_code: String? = null,
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Chat(
    val id: Long? = null,
    val first_name: String? = null,
    val username: String? = null,
    val type: String? = null,
)