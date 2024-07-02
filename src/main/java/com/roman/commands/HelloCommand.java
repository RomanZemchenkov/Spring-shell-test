package com.roman.commands;

import com.roman.exception.RegistrationException;
import org.springframework.shell.Availability;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.*;

@ShellComponent
@ShellCommandGroup("testGroup")
public class HelloCommand {

    private boolean connection;

    @ShellMethod(value = "connection method",key = "connection")
    public void connection(@ShellOption(value = {"--connection","-c"}) boolean connection){
        this.connection = connection;
    }

    @ShellMethodAvailability("availabilityConnectionCheck")
    @ShellMethod(value = "testHello",key = "testKey")
    public String hello(@ShellOption(defaultValue = "spring", value = {"--name","-n"},help = "Help message",arity = 2) String name){
        if(name == null || name.isEmpty()){
            throw new RegistrationException();
        }
        return "Hello, " + name;
    }


    @ExceptionResolver(RegistrationException.class)
    private CommandHandlingResult errorHandler(){
        return CommandHandlingResult.of("Exception");
    }


    public Availability availabilityConnectionCheck(){
        return connection ? Availability.available() : Availability.unavailable("you are not connected");
    }
}
