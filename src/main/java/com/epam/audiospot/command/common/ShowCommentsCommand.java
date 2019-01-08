package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.factory.CommandType;
import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCommentsCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        CommentService service = new CommentService();
        Long trackId = Long.parseLong(request.getParameter("trackId"));
        try {
            List<Comment> comments = service.findComments(trackId);
            if(comments.size()!=0){
                request.setAttribute("comments",comments);
                return CommandResult.forward(Forward.SHOW_COMMENTS.getPath());
            }else{
                return CommandType.SHOW_TRACKS.getCommand().execute(request,response);
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }

    }
}
