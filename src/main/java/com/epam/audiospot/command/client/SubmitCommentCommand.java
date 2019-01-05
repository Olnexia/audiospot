package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.command.admin.SubmitTrackCommand;
import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class SubmitCommentCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SubmitTrackCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String text = request.getParameter(Comment.TEXT_LABEL);
        Long trackId = Long.parseLong(request.getParameter(Comment.TRACK_ID_LABEL));
        LocalDateTime dateTime = LocalDateTime.now();
        Long userId;
        HttpSession session = request.getSession(false);
        if(session!=null){
            User user = (User)session.getAttribute("user");
            userId = user.getId();
        }else{
            throw new CommandExecutionException("Session does not exist");
        }

        Comment comment = new Comment(null,userId,trackId,text,dateTime);
        CommentService service = new CommentService();
        try{
            service.save(comment);
        }catch (ServiceException e){
            logger.error("An error occurred while adding track: ",e);
        }
        return CommandResult.redirect(Redirect.SHOW_PLAYLIST.getPath());
    }
}
