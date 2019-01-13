package com.epam.audiospot.command.factory;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.admin.AddTrackCommand;
import com.epam.audiospot.command.admin.ChangeClientStatusCommand;
import com.epam.audiospot.command.admin.ShowClientsCommand;
import com.epam.audiospot.command.admin.SubmitTrackCommand;
import com.epam.audiospot.command.client.*;
import com.epam.audiospot.command.common.HomeCommand;
import com.epam.audiospot.command.common.LoginCommand;
import com.epam.audiospot.command.common.LogoutCommand;
import com.epam.audiospot.command.common.ShowTracksCommand;
import com.epam.audiospot.exception.IllegalCommandException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CommandFactoryTest {

    @DataProvider
    public static Object[][] commands() {
        return new Object[][] {
                { "login",LoginCommand.class },
                { "home", HomeCommand.class},
                { "addTrack", AddTrackCommand.class},
                { "submitTrack", SubmitTrackCommand.class},
                { "showClients", ShowClientsCommand.class},
                { "changeClientStatus", ChangeClientStatusCommand.class},
                { "showPlaylist", ShowPlaylistCommand.class},
                { "submitComment", SubmitCommentCommand.class},
                { "showTracks", ShowTracksCommand.class},
                { "payOrder", PayForOrderCommand.class},
                { "submitPayment", SubmitPaymentCommand.class},
                { "logout", LogoutCommand.class},
        };
    }

    @Test
    @UseDataProvider("commands")
    public void shouldCreateCorrectCommand(String commandText,Class expectedCommandClass) throws IllegalCommandException {
        //given
        //when
        Command actual = CommandFactory.create(commandText);
        //then
        Assert.assertNotEquals(null,actual);
        Assert.assertEquals(expectedCommandClass,actual.getClass());
    }
}
