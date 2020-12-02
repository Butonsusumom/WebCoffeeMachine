package com.tsybulko.dao;

import com.tsybulko.entity.items.Item;

import java.util.List;

/**
 * DAO for operations with Item objects
 */

public interface ItemDAO {

    /**
     * @return Returns list of Item objects from database
     */

    List<Item> getAllItems();

    /**
     * @param itemName - The name of the item
     * @param count    - The parameter sets the quantity of the item
     * @return Return true if item updated successfully
     */

    boolean updateItem(String itemName, int count);

    /**@param name - The name of item
     * @return Returns the Item object from database
     */

    Item getItemByName(String name);

}
