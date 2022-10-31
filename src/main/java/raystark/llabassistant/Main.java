package raystark.llabassistant;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import reactor.core.publisher.Mono;

import static java.util.Objects.requireNonNull;
import static java.util.function.Predicate.not;

public class Main {
    public static void main(String[] args) {
        var token = System.getenv("DISCORD_BOT_TOKEN");

        requireNonNull(token, "Set the bot token string in the environment variable DISCORD_BOT_TOKEN.");

        DiscordClient
            .create(token)
            .withGateway(client -> client
                .on(MessageCreateEvent.class, event -> Mono
                    .just(event.getMessage())
                    .filter(m -> m
                        .getContent()
                        .equals("!test")
                    )
                    .flatMap(m -> m
                        .getAuthorAsMember()
                        .filter(not(Member::isBot))
                        .flatMap(u -> m
                            .getChannel()
                            .flatMap(c -> c
                                .createMessage("Hello %s".formatted(u.getNickname().orElse(u.getUsername())))
                            )
                        )
                    )
                )
            )
            .block();
    }
}
