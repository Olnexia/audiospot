package com.epam.audiospot.command;

public class CommandFactory {

    public static Command create(String command){
        switch (command){
            case "login":
                return new LoginCommand();
            default: throw new IllegalArgumentException();
        }
    }
}
