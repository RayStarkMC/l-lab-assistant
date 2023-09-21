package raystark.llabassistant

import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

fun main() {
    val token = System.getenv("BOT_TOKEN")!!
    val logger = Logger.DEFAULT

    val client = HttpClient(Java) {
        install(Logging) {
            this.logger = logger
            level = LogLevel.HEADERS
        }
    }
    runBlocking {
        val response: HttpResponse = client
            .get("https://discord.com/api/v10/applications/@me") {
                userAgent("DiscordBot (https://example.com, 0)")
                headers.append("Authorization", "Bot $token")
            }
        println(response.status)
        println(response.bodyAsText())
    }
    client.close()
}