package io.github.hexagonnico.undergroundjungle;

import io.github.hexagonnico.undergroundjungle.blocks.TempleChestBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UndergroundJungleBlockEntities {

    public static final BlockEntityType<TempleChestBlockEntity> TEMPLE_CHEST = BlockEntityType.Builder.create(TempleChestBlockEntity::new, UndergroundJungleBlocks.TEMPLE_CHEST).build(null);

    public static void registerBlockEntities() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(UndergroundJungleMod.ID, "temple_chest"), TEMPLE_CHEST);
    }
}
