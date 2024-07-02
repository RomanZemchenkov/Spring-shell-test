package com.roman.annotation_conf;


import org.springframework.shell.command.annotation.Command;

@Command(command = "you")
public class AnnotationCommandConfiguration {

    @Command(command = {"privet"},description = "opisanie")
    public String example(){
        return "Hello";
    }
}
