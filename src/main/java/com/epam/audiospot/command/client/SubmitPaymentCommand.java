package com.epam.audiospot.command.client;

import com.epam.audiospot.command.*;
import com.epam.audiospot.command.utils.PaymentVerifier;
import com.epam.audiospot.command.utils.PriceCalculator;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
import com.epam.audiospot.validator.complex.PaymentComplexValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SubmitPaymentCommand implements Command {
    private static final String PAY_MESSAGE_ATTR = "payMessage";
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

        PaymentComplexValidator validator = new PaymentComplexValidator(cardNumber,cvc,expiry);
        Optional<List<String>> validateMessages = validator.validate();
        if(validateMessages.isPresent()){
            request.setAttribute(PAY_MESSAGE_ATTR, validateMessages.get());
            return CommandResult.forward(Forward.PAY_ORDER.getPath());
        }

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_ATTR);

        OrderService service = new OrderService();
        Order order = service.findOrder(user.getId(),false);

        PriceCalculator calculator = new PriceCalculator();
        BigDecimal orderFinalPrice = calculator.calculateFinalPrice(order.getId(),new BigDecimal(user.getDiscount()));

        PaymentVerifier verifier = (cn, cv, e, p)-> true;  //dummy payment verifier
        if(verifier.verify(cardNumber,cvc,expiry,orderFinalPrice)){
            order.setPaid(true);
            service.saveOrder(order);
            logger.info("Payment of order " + order.getId() + " is successful.");
        }else {
            logger.info("Payment of order " + order.getId() + " not passed.");
        }

        return CommandResult.redirect(Redirect.SHOW_PLAYLIST.getPath());
    }
}
