package ru.yas.link_shortener.plugins

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.yas.link_shortener.database.links.LinkDTO
import ru.yas.link_shortener.database.links.Links

fun Application.configureRouting(database: MongoDatabase) {

    routing {

        get("/{url}") {
            val requestUrl = call.parameters["url"].toString()
            val shortLink = Links.read(database, requestUrl)
            if (shortLink.isNotEmpty()){ call.respondRedirect("//$shortLink") }
            call.respond(HttpStatusCode.NotFound)
        }

        post("/createUrl"){
            val requestData = call.receive<LinkDTO>()
            if (requestData.token == System.getenv("TOKEN")){
                call.respondText(Links.insert(database, requestData)) }
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
