package com.epam.audiospot.command.creator;

import com.epam.audiospot.command.Command;

public class CommandCreator {
    public static Command create(String commandText) {
        CommandType commandType = CommandType.getCurrentCommand(commandText);
        return commandType.getCommand();
    }
}
