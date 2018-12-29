package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Page;
import com.epam.audiospot.command.PaymentVerifier;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
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

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String cardNumber = request.getParameter(CARD_NUMBER_REQUEST_PARAMETER);
        String cvc = request.getParameter(CVC_NUMBER_REQUEST_PARAMETER);
        String expiry = request.getParameter(EXP_DATE_REQUEST_PARAMETER);

        //TODO think about order handling like where would be order setted to true
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
                }else {
                    //not enougth money
                }
            }else{
                //nothing to pay for
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.forward(Page.PAY_ORDER.getPath());
    }
}
