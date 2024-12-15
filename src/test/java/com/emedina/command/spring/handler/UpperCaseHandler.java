package com.emedina.command.spring.handler;

import com.emedina.sharedkernel.command.core.CommandHandlerReactive;
import com.emedina.command.spring.command.UpperCaseCommand;
import reactor.core.publisher.Mono;

public class UpperCaseHandler implements CommandHandlerReactive<UpperCaseCommand> {

    @Override
    public void handle(final Mono<UpperCaseCommand> command) {
        command
                .map(UpperCaseCommand::text)
                .map(String::toUpperCase)
                .doOnNext(System.out::print)
                .block();
    }
}
