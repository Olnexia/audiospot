package com.epam.audiospot.command.client;

import com.epam.audiospot.command.*;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class SubmitPaymentCommand implements Command {
    private static final String CARD_NUMBER_PARAM = "cardNumber";
    private static final String CVC_PARAM = "cvc";
    private static final String EXP_DATE_PARAM = "ccExp";
    private static final String USER_ATTR = "user";
    private static final Logger logger = LogManager.getLogger(SubmitPaymentCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String cardNumber = request.getParameter(CARD_NUMBER_PARAM);
        String cvc = request.getParameter(CVC_PARAM);
        String expiry = request.getParameter(EXP_DATE_PARAM);

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_ATTR);

        OrderService service = new OrderService();
        Order order = service.findOrder(user.getId(),false);

        BigDecimal orderTotalPrice = service.calculateTotalPrice(order.getId());

        PaymentVerifier verifier = (cn, cv, e, p)-> true;
        if(verifier.verify(cardNumber,cvc,expiry,orderTotalPrice)){
            order.setPaid(true);
            service.saveOrder(order);
            logger.info("Payment of order " + order.getId() + " is successful.");
        }else {
            logger.info("Payment of order " + order.getId() + " not passed.");
        }

        return CommandResult.redirect(Redirect.SHOW_PLAYLIST.getPath());
    }
}
