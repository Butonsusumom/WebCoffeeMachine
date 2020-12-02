package com.tsybulko.command.factory;

import com.tsybulko.command.Command;

public interface CommandFactory {

    Command getCommand(String path);
}
