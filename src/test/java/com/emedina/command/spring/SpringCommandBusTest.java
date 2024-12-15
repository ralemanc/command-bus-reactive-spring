package com.emedina.command.spring;

import com.emedina.command.spring.command.LowerCaseCommand;
import com.emedina.command.spring.command.UpperCaseCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@ContextConfiguration(classes = SpringCommandBusConfiguration.class)
class SpringCommandBusTest {

    @Autowired
    SpringCommandBusReactive commandBus;

    @Test
    void runLowerCaseCommand() {
        // Arrange
        final var lowerCaseCommand = new LowerCaseCommand("Spring Command Bus Command TEST");

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        // Act
        this.commandBus.execute(lowerCaseCommand);

        // Assert
        final String standardOutput = myOut.toString();
        assertThat(standardOutput).isEqualTo("spring command bus command test");
    }

    @Test
    void runUpperCaseCommand() {
        // Arrange
        final var upperCaseCommand = new UpperCaseCommand("Spring Command Bus Command TEST");
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        // Act
        this.commandBus.execute(upperCaseCommand);

        // Assert
        final String standardOutput = myOut.toString();
        assertThat(standardOutput).isEqualTo("SPRING COMMAND BUS COMMAND TEST");
    }
}