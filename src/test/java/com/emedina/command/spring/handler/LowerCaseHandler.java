package com.emedina.command.spring.handler;

import com.emedina.sharedkernel.command.core.CommandHandlerReactive;
import com.emedina.command.spring.command.LowerCaseCommand;
import reactor.core.publisher.Mono;

public class LowerCaseHandler implements CommandHandlerReactive<LowerCaseCommand> {

    @Override
    public void handle(final Mono<LowerCaseCommand> command) {
        command
                .map(LowerCaseCommand::text)
                .map(String::toLowerCase)
                .subscribe(System.out::print);
    }
}
