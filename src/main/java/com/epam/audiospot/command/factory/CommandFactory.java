package com.epam.audiospot.command.factory;

import com.epam.audiospot.command.Command;

public class CommandFactory {

    public static Command create(String commandText){
        CommandType commandType = CommandType.getCurrentCommand(commandText);
        return commandType.getCommand();
    }
}
