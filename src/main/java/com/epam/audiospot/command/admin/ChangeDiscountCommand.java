package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeDiscountCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        float discount = Float.parseFloat(request.getParameter(User.DISCOUNT_LABEL));
        Long userId = Long.parseLong(request.getParameter(User.ID_LABEL));

        UserService service = new UserService();

        User user = service.findUser(userId);
        user.setDiscount(discount);
        service.saveUser(user);

        return CommandResult.redirect(Redirect.SHOW_CLIENTS.getPath());
    }
}
