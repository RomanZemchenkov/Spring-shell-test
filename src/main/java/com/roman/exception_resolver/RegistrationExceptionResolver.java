package com.roman.exception_resolver;

import com.roman.exception.RegistrationException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.stereotype.Component;

@Component
public class RegistrationExceptionResolver implements CommandExceptionResolver {
    @Override
    public CommandHandlingResult resolve(Exception ex) {
        if(ex instanceof RegistrationException){
            return CommandHandlingResult.of("Hi, you command should be show how 'hey --name/-n youName'\n");
        }
        return null;
    }
}
