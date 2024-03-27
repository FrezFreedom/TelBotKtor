package com.example.services

import com.example.dtos.StatusDto
import com.example.dtos.UpdateDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import fuel.Fuel
import fuel.get
import io.github.cdimascio.dotenv.Dotenv

class TelegramService {
    private val baseUrl = "https://api.telegram.org/bot"
    private val env = Dotenv.load()
    private val token: String = env["TELEGRAM_TOKEN"] ?: throw Exception("TELEGRAM_TOKEN not exists in .env file!")

    suspend fun getUpdates(): UpdateDto {
        val data = Fuel.get("$baseUrl$token/getUpdates").body
        val mapper = jacksonObjectMapper()
        val strangeData: UpdateDto = mapper.readValue(data)

        return strangeData
    }

    suspend fun sendMessage(chatId: String, text: String, format: String): Boolean {
        return try {
            val response = Fuel.get("$baseUrl$token/sendMessage?chat_id=$chatId&parse_mode=$format&text=$text").body
            val objectMapper = jacksonObjectMapper()
            val status = objectMapper.readValue(response, StatusDto::class.java)
            println("----------------------------$status-----------------------------")
            status.ok
        } catch (e: Exception) {
            false
        }
    }
}