package com.emedina.command.spring;

import com.emedina.sharedkernel.command.Command;
import com.emedina.sharedkernel.command.core.CommandBus;
import com.emedina.sharedkernel.command.core.CommandHandlerReactive;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * Implementation of a command bus backed by Spring's registry.
 *
*/
class SpringCommandBusReactive implements CommandBus {

    private final Registry registry;

    /**
     * Creates a new instance with the given registry using constructor-based dependency injection.
     *
     * @param registry a wrapper around Spring's application context
     */
    public SpringCommandBusReactive(final Registry registry) {
        this.registry = registry;
    }

    /**
     * Delegates the handling of the command to the corresponding {@link Bean} from Spring.
     *
     * @param command the command object
     */
    @Override
    public <C extends Command> void execute(final C command) {
        CommandHandlerReactive<C> commandHandler = (CommandHandlerReactive<C>) this.registry.get(command.getClass());
        commandHandler.handle(Mono.just(command));
    }

}
