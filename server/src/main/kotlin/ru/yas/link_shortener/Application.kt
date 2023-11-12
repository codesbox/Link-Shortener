package ru.yas.link_shortener

import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.yas.link_shortener.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val client = MongoClient.create(connectionString = System.getenv("MONGODB_URI"))
    val database = client.getDatabase("Link-Shortener")

    configureRouting(database)
    configureSerialization()
}
