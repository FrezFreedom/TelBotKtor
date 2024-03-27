package com.example.plugins

import com.example.services.TelegramService
import com.example.dtos.*
import io.github.cdimascio.dotenv.Dotenv
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.application.environment.log.info("test")
            call.respondText("Hello World!")
        }

        get("/get") {
            val telegramService = TelegramService()
            val result = telegramService.getUpdates()
            call.respondText (result.toString())
        }

        post("/sendMessage"){
            val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
            val env = Dotenv.load()
            if(token == null || token != env["TOKEN"]){
                call.respond(HttpStatusCode.Unauthorized, "Unauthorized access")
                return@post
            }

            val messageDto = call.receive<MessageDto>()
            val telegramService = TelegramService()

            val statusDto = StatusDto(
                ok = telegramService.sendMessage(messageDto.chatId, messageDto.text, messageDto.format)
            )

            call.respond(statusDto)
        }
    }
}
