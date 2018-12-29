package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Page;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTrackCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        return CommandResult.forward(Page.ADD_TRACK.getPath());
    }
}
