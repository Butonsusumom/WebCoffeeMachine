package com.tsybulko.command;

import com.tsybulko.command.impl.*;
import com.tsybulko.command.impl.admin.drinks.AddDrinkCommand;
import com.tsybulko.command.impl.admin.drinks.AddServingsCommand;
import com.tsybulko.command.impl.admin.drinks.AddServingsPageCommand;
import com.tsybulko.command.impl.admin.drinks.DrinkManagementCommand;
import com.tsybulko.command.impl.admin.users.BlockUserCommand;
import com.tsybulko.command.impl.admin.users.UnblockUserCommand;
import com.tsybulko.command.impl.admin.users.UserEditCommand;
import com.tsybulko.command.impl.admin.users.UserListCommand;
import com.tsybulko.command.impl.user.*;
import com.tsybulko.command.impl.user.card.AddCardCommand;
import com.tsybulko.command.impl.user.card.AddMoneyCommand;
import com.tsybulko.command.impl.user.card.DeleteCardCommand;
import com.tsybulko.command.impl.user.card.EditCardCommand;
import com.tsybulko.command.impl.user.cart.AddDrinkToCartCommand;
import com.tsybulko.command.impl.user.cart.CartPageCommand;
import com.tsybulko.command.impl.user.cart.CheckoutCartCommand;
import com.tsybulko.command.impl.user.cart.DeleteDrinkFromCartCommand;
import com.tsybulko.command.redirect.*;
import com.tsybulko.command.redirect.admin.AddDrinkPageCommand;
import com.tsybulko.command.redirect.user.*;

/**
 * Represent request command attribute parameters.
 */
public enum CommandParameter {
    INDEX_PAGE(new IndexPageCommand()),
    DRINK_LIST(new DrinkListCommand()),

    REGISTRATION_PAGE(new RegistrationPageCommand()),
    REGISTER(new RegisterCommand()),
    SIGN_IN_PAGE(new SignInPageCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),

    USER_LIST(new UserListCommand()),
    USER_EDIT(new UserEditCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),

    DRINK_MANAGEMENT(new DrinkManagementCommand()),
    ADD_DRINK_PAGE(new AddDrinkPageCommand()),
    ADD_DRINK(new AddDrinkCommand()),
    ADD_SERVINGS_PAGE(new AddServingsPageCommand()),
    ADD_SERVINGS(new AddServingsCommand()),

    USER_PROFILE(new UserProfilePageCommand()),
    EDIT_PROFILE_PAGE(new EditProfilePageCommand()),
    EDIT_USER_PROFILE(new EditUserProfileCommand()),

    ADD_CARD_PAGE(new AddCardPageCommand()),
    ADD_CARD(new AddCardCommand()),
    EDIT_CARD_PAGE(new EditCardPageCommand()),
    EDIT_CARD(new EditCardCommand()),
    ADD_MONEY_PAGE(new AddMoneyPageCommand()),
    ADD_MONEY(new AddMoneyCommand()),
    DELETE_CARD(new DeleteCardCommand()),

    CART_PAGE(new CartPageCommand()),
    ADD_DRINK_TO_CART(new AddDrinkToCartCommand()),
    DELETE_DRINK_FROM_CART(new DeleteDrinkFromCartCommand()),
    CHECKOUT_CART(new CheckoutCartCommand());

    Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
