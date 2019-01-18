package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelOrderCommand implements Command {
    private static final String USER_ATTR = "user";
    private static final Logger logger = LogManager.getLogger(CancelOrderCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_ATTR);

        OrderService service = new OrderService();
        Order order = service.findOrder(user.getId(),false);
        service.deleteOrder(order);

        logger.info("Order " + order.getId() + " canceled");
        return CommandResult.forward(Forward.PAY_ORDER.getPath());
    }
}
