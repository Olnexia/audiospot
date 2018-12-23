package com.epam.audiospot.command;

import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.Playlist;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import com.epam.audiospot.service.OrderService;
import com.epam.audiospot.service.PlaylistService;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.mockito.ArgumentMatchers.any;

public class SubmitPaymentCommand implements Command {
    private static final String CARD_NUMBER_REQUEST_PARAMETER = "cardNumber";
    private static final String CVC_NUMBER_REQUEST_PARAMETER = "cvc";
    private static final String EXP_DATE_REQUEST_PARAMETER = "ccExp";
    private static final String USER_SESSION_PARAMETER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String cardNumber = request.getParameter(CARD_NUMBER_REQUEST_PARAMETER);
        String cvc = request.getParameter(CVC_NUMBER_REQUEST_PARAMETER);
        String expiry = request.getParameter(EXP_DATE_REQUEST_PARAMETER);

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_SESSION_PARAMETER);
        OrderService orderService = new OrderService();
        AudioTrackService trackService = new AudioTrackService();
        BigDecimal orderTotalPrice;
        try{
            Order order;
            List<AudioTrack> orderedTracks;
            Optional<Order> orderOptional = orderService.findOrder(user.getId(),false);
            if(orderOptional.isPresent()){
                order = orderOptional.get();
                orderedTracks = trackService.findOrderedTracks(order.getId());
                List<BigDecimal> prices = orderedTracks.stream().map(AudioTrack::getPrice).collect(Collectors.toList());
                orderTotalPrice = prices.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
            }else{
                //nothing to pay for
                return CommandResult.forward("/WEB-INF/pages/payorder.jsp");
            }

            PaymentVerifier verifier = Mockito.mock(PaymentVerifier.class);
            Mockito.when(verifier.verify(any(String.class),any(String.class),any(String.class),any(BigDecimal.class)))
                    .thenReturn(true);

            //transaction?

            if(verifier.verify(cardNumber,cvc,expiry,orderTotalPrice)) {
                order.setPaid(true);
                orderService.saveOrder(order);
                PlaylistService playlistService = new PlaylistService();

                Long playlistId = user.getPlaylistId();
                if(playlistId==null) {
                    Playlist playlist = Playlist.personal();
                    playlistService.savePlaylist(playlist);
                    playlistId = playlist.getId();
                }
                for(AudioTrack audioTrack:orderedTracks){
                    playlistService.addTrack(playlistId,audioTrack.getId());
                }
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.forward("/WEB-INF/pages/payorder.jsp");
    }
}
