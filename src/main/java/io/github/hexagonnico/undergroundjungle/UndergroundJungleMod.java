package io.github.hexagonnico.undergroundjungle;

import net.fabricmc.api.ModInitializer;

public class UndergroundJungleMod implements ModInitializer {

    public static final String ID = "underground_jungle";

    @Override
    public void onInitialize() {
        UndergroundJungleBlocks.registerBlocks();
        UndergroundJungleBlockEntities.registerBlockEntities();
        UndergroundJungleItems.registerItems();
        UndergroundJungleEntities.registerEntities();
        UndergroundJungleEntities.registerRenders();
        UndergroundJungleEntities.registerAttributes();
    }
}