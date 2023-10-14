package io.github.hexagonnico.undergroundjungle;

import io.github.hexagonnico.undergroundjungle.blocks.JungleVinesBlock;
import io.github.hexagonnico.undergroundjungle.blocks.JungleVinesPlantBlock;
import io.github.hexagonnico.undergroundjungle.blocks.MudGrassBlock;
import io.github.hexagonnico.undergroundjungle.blocks.TempleChestBlock;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class UndergroundJungleBlocks {

    private static final HashMap<String, Block> REGISTRY = new HashMap<>();

    public static final Block JUNGLE_GRASS = register("jungle_grass", new MudGrassBlock(AbstractBlock.Settings.copy(Blocks.MUD).sounds(BlockSoundGroup.GRASS).ticksRandomly(), "jungle_vegetation"));
    public static final Block TEMPLE_BRICKS = register("temple_bricks", new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().mapColor(MapColor.TERRACOTTA_BROWN).sounds(BlockSoundGroup.STONE).strength(30.0f, 1200.0f)));
    public static final Block CRACKED_TEMPLE_BRICKS = register("cracked_temple_bricks", new Block(AbstractBlock.Settings.copy(TEMPLE_BRICKS)));
    public static final Block MOSSY_TEMPLE_BRICKS = register("mossy_temple_bricks", new Block(AbstractBlock.Settings.copy(TEMPLE_BRICKS)));
    public static final Block CHISELED_TEMPLE_BRICKS = register("chiseled_temple_bricks", new Block(AbstractBlock.Settings.copy(TEMPLE_BRICKS)));
    public static final Block TEMPLE_BRICK_TILES = register("temple_brick_tiles", new Block(AbstractBlock.Settings.copy(TEMPLE_BRICKS)));
    public static final Block TEMPLE_BRICK_STAIRS = register("temple_brick_stairs", new StairsBlock(TEMPLE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(TEMPLE_BRICKS)));
    public static final Block TEMPLE_BRICK_SLAB = register("temple_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(TEMPLE_BRICKS)));
    public static final Block TEMPLE_BRICK_WALL = register("temple_brick_wall", new WallBlock(AbstractBlock.Settings.copy(TEMPLE_BRICKS)));
    public static final Block TEMPLE_BRICK_TILE_STAIRS = register("temple_brick_tile_stairs", new StairsBlock(TEMPLE_BRICK_TILES.getDefaultState(), AbstractBlock.Settings.copy(TEMPLE_BRICK_TILES)));
    public static final Block TEMPLE_BRICK_TILE_SLAB = register("temple_brick_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(TEMPLE_BRICK_TILES)));
    public static final Block TEMPLE_BRICK_TILE_WALL = register("temple_brick_tile_wall", new WallBlock(AbstractBlock.Settings.copy(TEMPLE_BRICK_TILES)));
    public static final Block TEMPLE_CHEST = register("temple_chest", new TempleChestBlock(AbstractBlock.Settings.copy(TEMPLE_BRICKS).strength(-1.0F, 3600000.0F).dropsNothing()));
    public static final Block JUNGLE_VINES_PLANT = register("jungle_vines_plant", new JungleVinesPlantBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.CAVE_VINES)));
    public static final Block JUNGLE_VINES = register("jungle_vines", new JungleVinesBlock(AbstractBlock.Settings.copy(JUNGLE_VINES_PLANT)));

    public static void registerBlocks() {
        REGISTRY.forEach((id, block) -> Registry.register(Registries.BLOCK, new Identifier(UndergroundJungleMod.ID, id), block));
    }

    private static Block register(String id, Block block) {
        REGISTRY.put(id, block);
        return block;
    }
}
