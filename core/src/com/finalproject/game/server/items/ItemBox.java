package com.finalproject.game.server.items;

import com.finalproject.game.server.items.weapons.instant.InstantWeapon;
import com.finalproject.game.server.items.weapons.melee.MeleeWeapon;
import com.finalproject.game.server.items.weapons.range.primary.PrimaryGun;
import com.finalproject.game.server.items.weapons.range.secondary.SecondaryGun;

import java.util.ArrayList;
import java.util.List;

public class ItemBox {


    public static final int MAX_INSTANT_WEAPONS = 3;
    public static final int MAX_ITEMS = MAX_INSTANT_WEAPONS + 3;


    protected Integer currentItemHeld = 2;
    protected List<Item> items = new ArrayList<>();

    protected PrimaryGun primaryGun;
    protected SecondaryGun secondaryGun;
    protected MeleeWeapon meleeWeapon;

    protected ArrayList<InstantWeapon> instantWeapons = new ArrayList<>();
    protected int instantWeaponsCount = 0;


    public ItemBox() {
        items = new ArrayList<>(MAX_ITEMS);
    }

    public void addItem(Item item) {
        if (item instanceof PrimaryGun) items.set(0, item);
        else if (item instanceof SecondaryGun) items.set(1, item);
        else if (item instanceof MeleeWeapon) items.set(2, item);
        else if (item instanceof InstantWeapon) {
            if (instantWeapons.size() > MAX_INSTANT_WEAPONS) return;

            instantWeapons.add((InstantWeapon) item);
        }
    }


    public void removeItem(Item item) {
        // TODO: set some validations and shit
        // TODO: no need, since we just dont allow that, not here...

        for (int i = 0; i < items.size(); i++) if (item.equals(items.get(i))) items.set(i, null);
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
