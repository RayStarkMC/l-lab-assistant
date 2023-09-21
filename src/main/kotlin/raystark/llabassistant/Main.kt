package raystark.llabassistant

import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.`object`.entity.Member
import discord4j.core.`object`.entity.channel.MessageChannel
import reactor.core.publisher.Mono
import java.util.*
import java.util.function.Predicate

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val token = System.getenv("DISCORD_BOT_TOKEN")
        Objects.requireNonNull(token, "Set the bot token string in the environment variable DISCORD_BOT_TOKEN.")
        DiscordClient
            .create(token)
            .withGateway { client ->
                client
                    .on(
                        MessageCreateEvent::class.java
                    ) { event ->
                        Mono
                            .just(event.message)
                            .filter { m -> m.content == "!test" }
                            .flatMap { m ->
                                m
                                    .authorAsMember
                                    .filter(Predicate.not { obj: Member -> obj.isBot })
                                    .flatMap { u ->
                                        m
                                            .channel
                                            .flatMap { c: MessageChannel ->
                                                c.createMessage("Hello %s".formatted(u.nickname.orElse(u.username)))
                                            }
                                    }
                            }
                    }
            }
            .block()
    }
}
