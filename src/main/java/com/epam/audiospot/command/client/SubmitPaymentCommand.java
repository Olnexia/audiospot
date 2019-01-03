package com.epam.audiospot.command.client;

import com.epam.audiospot.command.*;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class SubmitPaymentCommand implements Command {
    private static final String CARD_NUMBER_REQUEST_PARAMETER = "cardNumber";
    private static final String CVC_NUMBER_REQUEST_PARAMETER = "cvc";
    private static final String EXP_DATE_REQUEST_PARAMETER = "ccExp";
    private static final String USER_SESSION_PARAMETER = "user";
    private static final Logger logger = LogManager.getLogger(SubmitPaymentCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String cardNumber = request.getParameter(CARD_NUMBER_REQUEST_PARAMETER);
        String cvc = request.getParameter(CVC_NUMBER_REQUEST_PARAMETER);
        String expiry = request.getParameter(EXP_DATE_REQUEST_PARAMETER);
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_SESSION_PARAMETER);
        try{
            OrderService orderService = new OrderService();
            Optional<Order> orderOptional = orderService.findOrder(user.getId(),false);
            if(orderOptional.isPresent()){
                Order order = orderOptional.get();
                BigDecimal orderTotalPrice = orderService.calculateTotalPrice(order.getId());
                PaymentVerifier verifier = (cn, cv, e, p)-> true;
                if(verifier.verify(cardNumber,cvc,expiry,orderTotalPrice)){
                    order.setPaid(true);
                    orderService.saveOrder(order);
                    logger.info("Payment of order " + order.getId()+" is successful.");
                }else {
                    logger.info("Payment of order " + order.getId()+" not passed.");
                }
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.redirect(Redirect.SHOW_PLAYLIST.getPath());
    }
}
