package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowPlaylistCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        return CommandResult.forward("/WEB-INF/pages/playlist.jsp");
    }
}
