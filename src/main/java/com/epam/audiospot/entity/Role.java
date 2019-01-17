package com.epam.audiospot.entity;

import java.util.Arrays;
import java.util.List;

public enum Role {
    CLIENT("client", Arrays.asList("addComment","showTracks","cancelOrder","orderTrack","payOrder"
            ,"showPlaylist","submitComment","orderAlbum","showAlbums","showAudioSets","showComments"
            ,"viewAlbum","submitPayment","viewAudioSet","home","logout")),
    ADMIN("admin",Arrays.asList("addTrack","addAlbum","submitAlbum","changeClientStatus","showClients","changeDiscount"
            ,"submitTrack","showAlbums","showAudioSets","addAudioSet","showTracks","showComments","viewAlbum"
            ,"submitAudioSet","addToSet","viewAudioSet","addTracks","home","logout"));

    private String value;
    private List<String> permissions;

    Role(String value, List <String> permissions) {
        this.value = value;
        this.permissions = permissions;
    }

    public String getValue() {
        return value;
    }

    public List <String> getPermissions() {
        return permissions;
    }
}
