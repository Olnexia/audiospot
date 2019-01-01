package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import org.junit.Assert;
import org.junit.Test;

public class ConnectionFactoryTest {
    @Test
    public void shouldReturnNewConnectionWhenPropertiesIsCorrect() throws ConnectionException {
        //given
        //when
        ConnectionWrapper actual = ConnectionFactory.getInstance();
        //then
        Assert.assertNotNull(actual);
    }
}
