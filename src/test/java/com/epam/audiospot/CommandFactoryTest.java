package com.epam.audiospot;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandFactory;
import com.epam.audiospot.command.LoginCommand;
import com.epam.audiospot.exception.CommandCreationException;
import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTest {
    private static final String TEST_COMMAND = "login";

    @Test
    public void shouldCreateLoginCommandWhenInputIsLogin() throws CommandCreationException {
        //given
        //when
        Command actual = CommandFactory.create(TEST_COMMAND);
        //then
        Assert.assertNotEquals(null,actual);
        Assert.assertEquals(LoginCommand.class,actual.getClass());
    }
}
