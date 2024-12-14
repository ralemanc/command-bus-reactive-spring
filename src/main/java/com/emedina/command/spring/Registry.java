package com.emedina.command.spring;

import com.emedina.sharedkernel.command.Command;
import com.emedina.sharedkernel.command.core.CommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry that holds the mapping between a command and its handler using Spring's {@link ApplicationContext}.
 *
 * @author Enrique Medina Montenegro
 * @see CommandHandler
 */
public final class Registry {

    private Map<Class<? extends Command>, CommandProvider> providerMap = new HashMap<>();

    /**
     * Constructor-based dependency injection.
     *
     * @param applicationContext Spring's application context
     */
    public Registry(final ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForType(CommandHandler.class);
        for (String name : names) {
            this.register(applicationContext, name);
        }
    }

    /**
     * Looks up the name of the Bean (as a {@link CommandHandler}) in Spring's application context.
     *
     * @param applicationContext Spring's application context
     * @param name               of the bean as a command handler
     */
    private void register(final ApplicationContext applicationContext, final String name) {
        Class<CommandHandler<?>> handlerClass = (Class<CommandHandler<?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
        //TODO: Check the right value 1 or 0 for generics[]
        Class<? extends Command> commandType = (Class<? extends Command>) generics[0];

        this.providerMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
    }

    @SuppressWarnings("unchecked")
    <C extends Command> CommandHandler<C> get(final Class<C> commandClass) {
        return this.providerMap.get(commandClass).get();
    }

}
