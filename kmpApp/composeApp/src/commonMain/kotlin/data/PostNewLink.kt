package data

import data.models.LinkModel
import data.models.PostLinkModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

object PostNewLink {

    suspend fun post(link : LinkModel, httpClient: HttpClient): LinkModel {

        return httpClient.post("http://192.168.29.148:8090/createUrl") {
            contentType(ContentType.Application.Json)
            setBody(
                PostLinkModel(
                link = link.link,
                token = "qwerty"
            )
            )
        }.body()


    }

}