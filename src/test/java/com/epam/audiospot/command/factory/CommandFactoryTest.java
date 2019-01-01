package com.epam.audiospot.command.factory;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.common.LoginCommand;
import com.epam.audiospot.exception.IllegalCommandException;
import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTest {
    private static final String TEST_COMMAND = "login";

    @Test
    public void shouldCreateLoginCommandWhenInputIsLogin() throws IllegalCommandException {
        //given
        //when
        Command actual = CommandFactory.create(TEST_COMMAND);
        //then
        Assert.assertNotEquals(null,actual);
        Assert.assertEquals(LoginCommand.class,actual.getClass());
    }
}
