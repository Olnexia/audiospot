package com.epam.audiospot.repository.user;

import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FindClientsTest {

    @Test
    public void shouldFindClients()throws ServiceException {
        //given
        UserService userService = new UserService();
        //when
        List<User> actual = userService.findClients();
        //then
        Assert.assertEquals(1, actual.size());
    }
}
