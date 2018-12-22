package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Genre;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class SubmitTrackCommand implements Command {
    private static final String ARTIST_LABEL = "artist";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String title = request.getParameter(AudioTrack.TITLE_LABEL);
        BigDecimal price = new BigDecimal(request.getParameter(AudioTrack.PRICE_LABEL));
        int releaseYear = Integer.parseInt(request.getParameter(AudioTrack.RELEASE_YEAR_LABEL));
        Genre genre = Genre.valueOf(request.getParameter(AudioTrack.GENRE_LABEL).toUpperCase());
        String artistName = request.getParameter(ARTIST_LABEL);

        try{
            AudioTrackService service = new AudioTrackService();
            AudioTrack track = service.buildTrack(title,price,releaseYear,genre,artistName);
            service.submitTrack(track);
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }

        //here need some message for success

        return CommandResult.redirect("/WEB-INF/pages/addtrack.jsp");
    }
}
