package io.github.hexagonnico.undergroundjungle;

import io.github.hexagonnico.undergroundjungle.renderers.TempleChestItemRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class UndergroundJungleItems {

    private static final HashMap<String, Item> REGISTRY = new HashMap<>();

    public static final ToolMaterial TOOLS_TIER = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 256;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 14.0f;
        }

        @Override
        public float getAttackDamage() {
            return 3.0f;
        }

        @Override
        public int getMiningLevel() {
            return 4;
        }

        @Override
        public int getEnchantability() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    };

    public static final Item TEMPLE_KEY = register("temple_key", new Item(new Item.Settings().maxCount(1)), ItemGroups.TOOLS);
    public static final Item TEMPLE_SWORD = register("temple_sword", new SwordItem(TOOLS_TIER, 3, -2.4f, new Item.Settings()), ItemGroups.COMBAT);
    public static final Item TEMPLE_SHOVEL = register("temple_shovel", new ShovelItem(TOOLS_TIER, 1.5f, -3.0f, new Item.Settings()), ItemGroups.TOOLS);
    public static final Item TEMPLE_PICKAXE = register("temple_pickaxe", new PickaxeItem(TOOLS_TIER, 1, -2.8f, new Item.Settings()), ItemGroups.TOOLS);
    public static final Item TEMPLE_AXE = register("temple_axe", new AxeItem(TOOLS_TIER, 5.0f, -3.0f, new Item.Settings()), ItemGroups.TOOLS, ItemGroups.COMBAT);
    public static final Item TEMPLE_HOE = register("temple_hoe", new HoeItem(TOOLS_TIER, -3, 0.0f, new Item.Settings()), ItemGroups.TOOLS);
    public static final Item JUNGLE_GRASS = register("jungle_grass", new BlockItem(UndergroundJungleBlocks.JUNGLE_GRASS, new Item.Settings()), ItemGroups.NATURAL);
    public static final Item TEMPLE_BRICKS = register("temple_bricks", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICKS, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item CRACKED_TEMPLE_BRICKS = register("cracked_temple_bricks", new BlockItem(UndergroundJungleBlocks.CRACKED_TEMPLE_BRICKS, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item MOSSY_TEMPLE_BRICKS = register("mossy_temple_bricks", new BlockItem(UndergroundJungleBlocks.MOSSY_TEMPLE_BRICKS, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item CHISELED_TEMPLE_BRICKS = register("chiseled_temple_bricks", new BlockItem(UndergroundJungleBlocks.CHISELED_TEMPLE_BRICKS, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_BRICK_TILES = register("temple_brick_tiles", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICK_TILES, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_BRICK_STAIRS = register("temple_brick_stairs", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICK_STAIRS, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_BRICK_SLAB = register("temple_brick_slab", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICK_SLAB, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_BRICK_WALL = register("temple_brick_wall", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICK_WALL, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_BRICK_TILE_STAIRS = register("temple_brick_tile_stairs", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICK_TILE_STAIRS, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_BRICK_TILE_SLAB = register("temple_brick_tile_slab", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICK_TILE_SLAB, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_BRICK_TILE_WALL = register("temple_brick_tile_wall", new BlockItem(UndergroundJungleBlocks.TEMPLE_BRICK_TILE_WALL, new Item.Settings()), ItemGroups.BUILDING_BLOCKS);
    public static final Item TEMPLE_CHEST = register("temple_chest", new BlockItem(UndergroundJungleBlocks.TEMPLE_CHEST, new Item.Settings()), ItemGroups.FUNCTIONAL);
    public static final Item JUNGLE_VINES = register("jungle_vines", new BlockItem(UndergroundJungleBlocks.JUNGLE_VINES, new Item.Settings()), ItemGroups.NATURAL);
    public static final Item MOSSY_SKELETON_SPAWN_EGG = register("mossy_skeleton_spawn_egg", new SpawnEggItem(UndergroundJungleEntities.MOSSY_SKELETON, 12698049, 7969893, new Item.Settings()), ItemGroups.SPAWN_EGGS);
    public static final Item JUNGLE_ZOMBIE_SPAWN_EGG = register("jungle_zombie_spawn_egg", new SpawnEggItem(UndergroundJungleEntities.JUNGLE_ZOMBIE, 44975, 9945732, new Item.Settings()), ItemGroups.SPAWN_EGGS);

    public static void registerItems() {
        REGISTRY.forEach((id, item) -> Registry.register(Registries.ITEM, new Identifier(UndergroundJungleMod.ID, id), item));
    }

    private static Item register(String id, Item item, ItemGroup... itemGroups) {
        REGISTRY.put(id, item);
        for(ItemGroup itemGroup : itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(listener -> listener.add(item));
        }
        return item;
    }

    public static void registerRenderers() {
        BuiltinItemRendererRegistry.INSTANCE.register(TEMPLE_CHEST, new TempleChestItemRenderer());
    }
}
