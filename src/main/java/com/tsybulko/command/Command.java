package com.tsybulko.command;

import com.tsybulko.command.factory.impl.CommandFactoryImpl;
import com.tsybulko.services.factory.ServiceFactory;
import com.tsybulko.services.factory.impl.ServiceFactoryImpl;
import com.tsybulko.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    ServiceFactory SERVICE_FACTORY = ServiceFactoryImpl.getServiceFactoryInstance();
    CommandFactoryImpl COMMAND_FACTORY = CommandFactoryImpl.getCommandFactoryImplInstance();
    UserValidator USER_VALIDATOR = UserValidator.getUserValidatorInstance();

    String execute(HttpServletRequest request);
}
