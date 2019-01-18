package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import com.epam.audiospot.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PayForOrderCommand implements Command {
    private static final String USER_PARAM = "user";
    private static final String ORDER_ID_ATTR = "orderId";
    private static final String ORDERED_TRACKS_ATTR = "orderedTracks";
    private static final String ORDER_TOTAL_PRICE_ATTR = "orderTotalPrice";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        OrderService orderService = new OrderService();
        AudioTrackService trackService = new AudioTrackService();
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_PARAM);

        Order order = orderService.findOrder(user.getId(),false);

        Long orderId = order.getId();
        List<AudioTrack> orderedTracks = trackService.findOrderedTracks(orderId);
        List<BigDecimal> prices = orderedTracks.stream().map(AudioTrack::getPrice).collect(Collectors.toList());
        BigDecimal orderTotalPrice = prices.stream().reduce(BigDecimal.ZERO,BigDecimal::add);

        request.setAttribute(ORDER_ID_ATTR,orderId);
        request.setAttribute(ORDERED_TRACKS_ATTR,orderedTracks);
        request.setAttribute(ORDER_TOTAL_PRICE_ATTR,orderTotalPrice);

        return CommandResult.forward(Forward.PAY_ORDER.getPath());
    }
}
