package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Page;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import com.epam.audiospot.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PayForOrderCommand implements Command {
    private static final String USER_SESSION_PARAMETER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        OrderService orderService = new OrderService();
        AudioTrackService trackService = new AudioTrackService();
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_SESSION_PARAMETER);
        try{
            Optional<Order> order = orderService.findOrder(user.getId(),false);
            if(order.isPresent()){
                Long orderId = order.get().getId();
                request.setAttribute("orderId",orderId);
                List<AudioTrack> orderedTracks = trackService.findOrderedTracks(orderId);
                request.setAttribute("orderedTracks",orderedTracks);
                List<BigDecimal> prices = orderedTracks.stream().map(AudioTrack::getPrice).collect(Collectors.toList());
                BigDecimal orderTotalPrice = prices.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
                request.setAttribute("orderTotalPrice",orderTotalPrice);
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.forward(Page.PAY_ORDER.getPath());
    }
}
