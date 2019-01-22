package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.command.utils.QuoteEscape;
import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.CommentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class SubmitCommentCommand implements Command {
    private static final String USER_ATTR = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String text = request.getParameter(Comment.TEXT_LABEL);
        Long trackId = Long.parseLong(request.getParameter(Comment.TRACK_ID_LABEL));
        LocalDateTime dateTime = LocalDateTime.now();

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_ATTR);

        QuoteEscape quoteEscape = new QuoteEscape();
        text = quoteEscape.escape(text);

        Comment comment = new Comment(null,user,trackId,text,dateTime);
        CommentService service = new CommentService();
        service.saveComment(comment);

        return CommandResult.redirect(Redirect.SHOW_PLAYLIST.getPath());
    }
}
