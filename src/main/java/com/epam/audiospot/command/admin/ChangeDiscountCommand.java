package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangeDiscountCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        int discount = Integer.parseInt(request.getParameter(User.DISCOUNT_LABEL));
        Long userId = Long.parseLong(request.getParameter(User.ID_LABEL));

        UserService service = new UserService();
        try{
            Optional<User> userOptional= service.findUser(userId);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                user.setDiscount(discount);
                service.saveUser(user);
            }else{
                throw new CommandExecutionException("User is missing");
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.redirect(Redirect.SHOW_CLIENTS.getPath());
    }
}
