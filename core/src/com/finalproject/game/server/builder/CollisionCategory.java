package com.finalproject.game.server.builder;

public class CollisionCategory {
    public static final short CATEGORY_PLAYER = 0x0001;  // 0000000000000001 in binary
    public static final short CATEGORY_ENEMY = 0x0002;   // 0000000000000010 in binary
    public static final short CATEGORY_PROJECTILE = 0x0004; // 0000000000000100 in binary
    // Add more categories as needed
}
