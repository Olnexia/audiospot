package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTrackToSetCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        Long trackId = Long.parseLong(request.getParameter("trackId"));
        Long audioSetId = Long.parseLong(request.getParameter("audioSetId"));
        AudioSetService service = new AudioSetService();
        try{
            service.addToSet(audioSetId,trackId);
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.redirect(Redirect.ADD_TO_SET.getPath()+"&audioSetId="+audioSetId);

    }
}
