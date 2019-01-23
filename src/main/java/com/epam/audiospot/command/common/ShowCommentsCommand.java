package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.CommentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCommentsCommand implements Command {
    private static final String TRACK_ID_PARAM = "trackId";
    private static final String COMMENTS_ATTR = "comments";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long trackId = Long.parseLong(request.getParameter(TRACK_ID_PARAM));

        CommentService service = new CommentService();
        List<Comment> comments = service.findComments(trackId);

        request.setAttribute(TRACK_ID_PARAM,trackId);
        request.setAttribute(COMMENTS_ATTR,comments);
        return CommandResult.forward(Forward.SHOW_COMMENTS.getPath());
    }
}
