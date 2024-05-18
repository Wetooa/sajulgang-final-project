package com.finalproject.game.server.items;

import java.util.ArrayList;
import java.util.List;

public class ItemBox {


    public static final int MAX_ITEMS = 10;

    protected int currentItemHeld = 0;
    protected List<Item> items = new ArrayList<>();

    public ItemBox() {
    }

    public void addItem(Item item) {
        if (items.size() == MAX_ITEMS) return;
        items.add(item);
    }


    public void removeItem(Item item) {
        // TODO: set some validations and shit
        items.remove(item);
    }

    public void scrollItemUp() {
        currentItemHeld = (currentItemHeld + 1) % MAX_ITEMS;
    }

    public void scrollItemDown() {
        currentItemHeld = (currentItemHeld - 1) == -1 ? MAX_ITEMS - 1 : currentItemHeld - 1;
    }

    public Item getHeldItem() {
        return items.get(currentItemHeld);
    }


}
