package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class SubmitCommentCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String text = request.getParameter(Comment.TEXT_LABEL);
        LocalDate date = LocalDate.now();
        Long userId;
        HttpSession session = request.getSession(false);
        if(session!=null){
            User user = (User)session.getAttribute("user");
            userId = user.getId();
        }
//        BigDecimal price = new BigDecimal(request.getParameter(AudioTrack.PRICE_LABEL));
//        int releaseYear = Integer.parseInt(request.getParameter(AudioTrack.RELEASE_YEAR_LABEL));
//        Genre genre = Genre.valueOf(request.getParameter(AudioTrack.GENRE_LABEL).toUpperCase());
//        String artistName = request.getParameter(ARTIST_LABEL);
//
//        AudioTrackService service = new AudioTrackService();
//        CommandResult commandResult;
//        try{
//            AudioTrack track = service.buildTrack(title,price,releaseYear,genre,artistName);
//            service.submitTrack(track);
//            logger.info("New track with id "+track.getId()+" added.");
//            commandResult = CommandResult.redirect(Redirect.ADD_TRACK.getPath());
//        }catch (ServiceException e){
//            logger.error("An error occurred while adding track: ",e);
//            commandResult = CommandResult.forward(Forward.ADD_TRACK.getPath()); //also set some text message?
//        }
//
//        //TODO here need some message for success
//
        return null;
    }
}
