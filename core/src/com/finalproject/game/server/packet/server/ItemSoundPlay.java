package com.finalproject.game.server.packet.server;

import com.finalproject.game.server.items.Item;

public class ItemSoundPlay {
    public Item.ItemType itemType = Item.ItemType.HANDGUN;

    public ItemSoundPlay() {
    }

    public ItemSoundPlay(Item.ItemType itemType) {
        this.itemType = itemType;
    }
}
