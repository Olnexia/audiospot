package com.epam.audiospot.command.creator;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.admin.*;
import com.epam.audiospot.command.client.*;
import com.epam.audiospot.command.common.*;
import com.epam.audiospot.exception.IllegalCommandException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CommandCreatorTest {
    @DataProvider
    public static Object[][] commands() {
        return new Object[][]{
                {"login", LoginCommand.class},
                {"register", RegisterCommand.class},
                {"home", HomeCommand.class},
                {"addTrack", AddTrackCommand.class},
                {"submitTrack", SubmitTrackCommand.class},
                {"showClients", ShowClientsCommand.class},
                {"changeClientStatus", ChangeClientStatusCommand.class},
                {"showPlaylist", ShowPlaylistCommand.class},
                {"submitComment", SubmitCommentCommand.class},
                {"showTracks", ShowTracksCommand.class},
                {"payOrder", PayForOrderCommand.class},
                {"submitPayment", SubmitPaymentCommand.class},
                {"addAlbum", AddAlbumCommand.class},
                {"submitAlbum", SubmitAlbumCommand.class},
                {"showAlbums", ShowAlbumsCommand.class},
                {"viewAlbum", ViewAlbumCommand.class},
                {"orderAlbum", OrderAlbumCommand.class},
                {"changeDiscount", ChangeDiscountCommand.class},
                {"showComments", ShowCommentsCommand.class},
                {"orderTrack", OrderTrackCommand.class},
                {"cancelOrder", CancelOrderCommand.class},
                {"addAudioSet", AddAudioSetCommand.class},
                {"submitAudioSet", SubmitAudioSetCommand.class},
                {"showAudioSets", ShowAudioSetsCommand.class},
                {"viewAudioSet", ViewAudioSetCommand.class},
                {"addTracks", SelectTracksToSetCommand.class},
                {"addToSet", AddTrackToSetCommand.class},
                {"orderAudioSet", OrderAudioSetCommand.class},
                {"logout", LogoutCommand.class},
        };
    }

    @Test
    @UseDataProvider("commands")
    public void shouldCreateCorrectCommand(String commandText, Class expectedCommandClass) throws IllegalCommandException {
        //given
        //when
        Command actual = CommandCreator.create(commandText);
        //then
        Assert.assertNotEquals(null, actual);
        Assert.assertEquals(expectedCommandClass, actual.getClass());
    }
}
