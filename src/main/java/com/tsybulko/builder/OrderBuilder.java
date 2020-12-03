package com.tsybulko.builder;

import com.tsybulko.entity.Drink;
import com.tsybulko.entity.Order;
import com.tsybulko.entity.User;

/**
 * Order builder.
 */

public class OrderBuilder {
    /**
     * Id.
     */
    private int id;
    /**
     * User.
     */
    private User user;
    /**
     * Drink.
     */
    private Drink drink;

    /**
     * Constructor without parameters.
     */
    public OrderBuilder() {
    }

    /**
     * Setter.
     *
     * @param id entity id.
     * @return @see OrderBuilder
     */
    public OrderBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Setter.
     *
     * @param user entity user.
     * @return @see OrderBuilder
     */
    public OrderBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Setter.
     *
     * @param drink entity drink.
     * @return @see OrderBuilder
     */
    public OrderBuilder setDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    /**
     * Getter.
     *
     * @return @see OrderBuilder with builded poles.
     */
    public Order getResult() {
        return new Order(id, user, drink);
    }
}
