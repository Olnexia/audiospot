package com.epam.audiospot.service;

import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import java.util.Optional;

public class UserServiceTest {
    private static final String TEST_LOGIN = "Olnexia";
    private static final String TEST_PASSWORD = "123";

    @Test
    public void shouldReturnListOfUsers() throws ServiceException {
        //given
        UserService userService = new UserService();
        //when
        Optional<User> actualOptional = userService.login(TEST_LOGIN,TEST_PASSWORD);
        //then
        Assert.assertTrue(actualOptional.isPresent());
        User actualUser = actualOptional.get();
        Assert.assertEquals(1,(long)actualUser.getId());
        Assert.assertEquals("Andrey",actualUser.getName());
        Assert.assertEquals("Losik",actualUser.getSurname());
        Assert.assertEquals("Olnexia",actualUser.getLogin());
        Assert.assertEquals(Role.ADMIN,actualUser.getRole());
        Assert.assertEquals(0,actualUser.getDiscount());
        Assert.assertTrue(actualUser.isActive());
    }
}
