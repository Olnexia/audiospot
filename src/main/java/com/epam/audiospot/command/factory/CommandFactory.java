package com.epam.audiospot.command.factory;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.exception.IllegalCommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);

    public static Command create(String commandText){
        try{
            CommandType commandType = CommandType.getCurrentCommand(commandText);
            return commandType.getCommand();
        }catch (IllegalArgumentException e) {
            logger.error("An attempt to use illegal command "+commandText);
            throw new IllegalCommandException(e.getMessage(),e);
        }
    }
}
