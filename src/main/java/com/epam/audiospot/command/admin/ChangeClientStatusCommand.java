package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.factory.CommandType;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangeClientStatusCommand implements Command {
    private static final String USER_ID_REQUEST_PARAMETER = "userId";
    private static final Logger logger = LogManager.getLogger(ChangeClientStatusCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        Long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAMETER));
        UserService service = new UserService();
        try{
            Optional<User> userOptional = service.findUser(userId);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                boolean status = user.isActive();
                user.setActive(!status);
                service.saveUser(user);
                if(user.isActive()){
                    logger.info("User with id "+user.getId()+" unblocked.");
                }else{
                    logger.info("User with id "+user.getId()+" blocked.");
                }
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        Command showClientsCommand = CommandType.SHOW_CLIENTS.getCommand();
        return showClientsCommand.execute(request,response);
    }
}
