package com.tsybulko.command.factory.impl;

import com.tsybulko.command.Command;
import com.tsybulko.command.factory.CommandFactory;
import com.tsybulko.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactoryImpl implements CommandFactory{
    private static CommandFactoryImpl commandFactoryImplInstance;
    private Map<String, Command> commandMap;

    private CommandFactoryImpl() {
        commandMap = new HashMap<>();
        commandMap.put("/machine/index", new IndexCommand());
        commandMap.put("/machine/login", new LoginCommand());
        commandMap.put("/machine/registration", new RegistrationCommand());
        commandMap.put("/machine/fillContainer", new FillContainerCommand());
        commandMap.put("/machine/fillingHistory", new GetFillsHistoryCommand());
        commandMap.put("/machine/personalAccount", new PersonalAccountCommand());
        commandMap.put("/machine/makeDrink", new MakeDrinkCommand());
        commandMap.put("/machine/replenishAccount", new ReplenishAccountCommand());
    }

    public static CommandFactoryImpl getCommandFactoryImplInstance() {
        if (commandFactoryImplInstance == null) {
            synchronized (CommandFactoryImpl.class) {
                if (commandFactoryImplInstance == null)
                    commandFactoryImplInstance = new CommandFactoryImpl();
            }
        }
        return commandFactoryImplInstance;
    }

    public synchronized Command getCommand(String path) {
        return commandMap.get(path);
    }




}
