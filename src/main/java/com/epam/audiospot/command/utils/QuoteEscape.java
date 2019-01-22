package com.epam.audiospot.command.utils;

public class QuoteEscape {
    public String escape(String unshielded){
        return unshielded.replaceAll("'","\\\\'")
                .replaceAll("\"","\\\\\"");
    }
}
