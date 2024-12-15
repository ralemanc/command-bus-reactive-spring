package com.emedina.sharedkernel.command.core;

import com.emedina.sharedkernel.command.Command;
import reactor.core.publisher.Mono;

/**
 * A handler for a {@link Command}. Notice that it does not return any value.
 *
 * @param <C> type of command
 *
 * @see <a href="https://martinfowler.com/bliki/CommandQuerySeparation.html">CQS - Command-Query Separation</a>
 */
public interface CommandHandlerReactive<C extends Command> {

    /**
     * Handles the command.
     *
     * @param command command to handle
     *
     */
    void handle(final Mono<C> command);

}