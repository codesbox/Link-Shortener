package ru.yas.link_shortener.database.links

import com.mongodb.MongoException
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlin.random.Random
import com.mongodb.client.model.Filters.eq
import kotlinx.coroutines.flow.firstOrNull

object Links {

    private const val COLLECTION_NAME = "Links"

    private const val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

    private const val MIN_LENGTH: Int = 1
    private const val MAX_LENGTH: Int = 2

    private fun generateRandomString(): String {
        val characters = ALPHABET
        val random = Random
        val length = random.nextInt(MIN_LENGTH, MAX_LENGTH + 1)
        return (1..length)
            .map { characters[random.nextInt(0, characters.length)] }
            .joinToString("")
    }

    suspend fun insert(database: MongoDatabase, linkDTO: LinkDTO): String{
        val collection = database.getCollection<Link>(collectionName = COLLECTION_NAME)
        val link = Link(
            id = generateRandomString(),
            link = linkDTO.link.removePrefix("https://").removePrefix("http://")
        )
        return try{
            collection.insertOne(link)
            link.id
        }catch (me: MongoException){
            insert(database, linkDTO)
        }
    }

    suspend fun read(database: MongoDatabase, requestUrl: String): String {
        val collection = database.getCollection<Link>(collectionName = COLLECTION_NAME)
        val result = collection.find<Link>(eq("_id", requestUrl)).firstOrNull()
        return result?.link ?: ""
    }

}