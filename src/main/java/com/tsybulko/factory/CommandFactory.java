package com.tsybulko.factory;

import com.tsybulko.command.Command;
import com.tsybulko.command.CommandParameter;

/**
 * Factory for command. Build command by command name.
 */
public enum CommandFactory {
    INSTANCE;

    public Command getCommand(String commandName) {
        return CommandParameter.valueOf(commandName).getCommand();
    }
}
