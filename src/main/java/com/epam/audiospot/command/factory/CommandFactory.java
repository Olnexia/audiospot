package com.epam.audiospot.command.factory;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.exception.CommandCreationException;

public class CommandFactory {

    public static Command create(String commandText)throws CommandCreationException{
        try{
            CommandType commandType = CommandType.getCurrentCommand(commandText);
            return commandType.getCommand();
        }catch (IllegalArgumentException e) {
            throw new CommandCreationException(e.getMessage(),e); //TODO mb extends runtime exc?
        }
    }
}
