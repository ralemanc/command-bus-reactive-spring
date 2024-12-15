package com.emedina.command.spring;

import com.emedina.command.spring.handler.LowerCaseHandler;
import com.emedina.command.spring.handler.UpperCaseHandler;
import com.emedina.sharedkernel.command.Command;
import com.emedina.sharedkernel.command.core.CommandHandlerReactive;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringCommandBusConfiguration {

    @Bean
    Registry registry(final List<CommandHandlerReactive<? extends Command>> commandHandlers) {
            return new Registry(commandHandlers);
    }

    @Bean
    SpringCommandBusReactive springCommandBus(final Registry registry) {
        return new SpringCommandBusReactive(registry);
    }

    @Bean
    LowerCaseHandler lowerCaseHandler() {
        return new LowerCaseHandler();
    }

    @Bean
    UpperCaseHandler upperCaseHandler() {
        return new UpperCaseHandler();
    }
}
