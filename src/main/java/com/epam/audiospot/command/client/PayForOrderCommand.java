package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.Order;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import com.epam.audiospot.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PayForOrderCommand implements Command {
    private static final String USER_PARAM = "user";
    private static final String ORDER_ID_ATTR = "orderId";
    private static final String ORDERED_TRACKS_ATTR = "orderedTracks";
    private static final String ORDER_TOTAL_PRICE_ATTR = "orderTotalPrice";
    private static final String ORDER_FINAL_PRICE_ATTR = "orderFinalPrice";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER_PARAM);

        OrderService orderService = new OrderService();
        Optional <Order> orderOptional = orderService.findOptionalOrder(user.getId(), false);
        if (orderOptional.isPresent()) {
            Long orderId = orderOptional.get().getId();
            AudioTrackService trackService = new AudioTrackService();
            List <AudioTrack> orderedTracks = trackService.findOrderedTracks(orderId);

            BigDecimal orderTotalPrice = orderService.calculateTotalPrice(orderId);
            BigDecimal userDiscount = new BigDecimal(user.getDiscount());
            BigDecimal orderFinalPrice = orderService.calculateFinalPrice(orderId, userDiscount);

            request.setAttribute(ORDER_ID_ATTR, orderId);
            request.setAttribute(ORDERED_TRACKS_ATTR, orderedTracks);
            request.setAttribute(ORDER_TOTAL_PRICE_ATTR, orderTotalPrice);
            request.setAttribute(ORDER_FINAL_PRICE_ATTR, orderFinalPrice);
        }
        return CommandResult.forward(Forward.PAY_ORDER.getPath());
    }
}
