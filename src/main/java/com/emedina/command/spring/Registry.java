package com.emedina.command.spring;

import com.emedina.sharedkernel.command.Command;
import com.emedina.sharedkernel.command.core.CommandHandler;
import com.emedina.sharedkernel.command.core.CommandHandlerReactive;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A registry that holds the mapping between a command and its handler using Spring's {@link ApplicationContext}.
 *
 * @author Enrique Medina Montenegro
 * @see CommandHandler
 */
final class Registry {

    private final Map<Class< ? extends Command>, CommandHandlerReactive<? extends Command>> commandHandlerMap = new HashMap<>();

    /**
     * Constructor-based dependency injection.
     *
     * @param commandHandlers Spring's application context
     */
    public Registry(final List<CommandHandlerReactive<? extends Command>> commandHandlers) {
        for (final CommandHandlerReactive<? extends Command> commandHandler : commandHandlers) {
            Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(commandHandler.getClass(), CommandHandlerReactive.class);
            Class<? extends Command> commandType = (Class<? extends Command>) generics[0];
            this.commandHandlerMap.put(commandType, commandHandler);
        }
    }


    @SuppressWarnings("unchecked")
    <C extends Command> CommandHandlerReactive<C> get(final Class<C> commandClass) {
        return (CommandHandlerReactive<C>) this.commandHandlerMap.get(commandClass);
    }

}
