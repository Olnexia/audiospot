package com.epam.audiospot.repository.user;

import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class LoginTest {
    private static final String TEST_LOGIN = "Olnexia";
    private static final String TEST_PASSWORD = "123";

    @Test
    public void shouldReturnListOfUsers() throws CommandExecutionException {
        //given
        UserService userService = new UserService();
        //when
        Optional<User> actual =userService.login(TEST_LOGIN,TEST_PASSWORD);
        //then
        Assert.assertTrue(actual.isPresent());
    }
}
