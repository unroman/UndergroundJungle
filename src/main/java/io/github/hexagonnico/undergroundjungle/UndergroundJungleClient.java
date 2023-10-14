package io.github.hexagonnico.undergroundjungle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class UndergroundJungleClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        UndergroundJungleBlockEntities.registerRenderers();
        UndergroundJungleBlocks.registerRenderers();
        UndergroundJungleItems.registerRenderers();
        UndergroundJungleEntities.registerRenderers();
    }
}