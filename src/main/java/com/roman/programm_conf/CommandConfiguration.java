package com.roman.programm_conf;

import com.roman.exception.RegistrationException;
import com.roman.exception_resolver.RegistrationExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Availability;
import org.springframework.shell.command.CommandRegistration;

@Configuration
public class CommandConfiguration {

    private boolean registration;

    @Bean
    public CommandRegistration registration() {
        return CommandRegistration.builder()
                .command("registration")
                .group("Hello group")
                .withOption()
                .longNames("registration")
                .shortNames('r')
                .type(boolean.class)
                .and()
                .withTarget()
                .function(com -> this.registration = com.getOptionValue("registration"))
                .and()
                .build();
    }

    @Bean
    public CommandRegistration helloProgramCommand() {
        return CommandRegistration.builder()
                .command("hey")
                .description("this command say 'hey'.")
                .availability(() -> registration ? Availability.available() : Availability.unavailable("You are not registration"))
                .group("Hello group")
                .withTarget()
                .function(com -> {
                    String name = com.getOptionValue("name");
                    if (name == null || name.isEmpty()) {
                        throw new RegistrationException();
                    }
                    return "Hello, " + name + "!";
                })
                .and()
                .withOption()
                    .longNames("name")
                    .shortNames('N')
//                    .required()
                    .type(String.class)
                    .and()
                .withErrorHandling()
                    .resolver(new RegistrationExceptionResolver())
                    .and()
//                .hidden(true)
                .withHelpOptions()
                    .enabled(true)
                    .command("help")
                    .longNames("help")
                    .shortNames('h')
                    .and()
                .build();
    }
}
