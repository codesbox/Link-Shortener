package data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json


object KtorClient {

    val client by lazy {
        HttpClient(OkHttp){
            install(ContentNegotiation){
                json()
            }
        }
    }

}