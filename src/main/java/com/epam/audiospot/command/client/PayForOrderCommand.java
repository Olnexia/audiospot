package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayForOrderCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
