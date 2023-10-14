package io.github.hexagonnico.undergroundjungle;

import io.github.hexagonnico.undergroundjungle.entities.JungleZombie;
import io.github.hexagonnico.undergroundjungle.renderers.JungleZombieRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class UndergroundJungleEntities {

    private static final HashMap<String, EntityType<?>> REGISTER = new HashMap<>();

//    public static final EntityType<MossySkeleton> MOSSY_SKELETON = register("mossy_skeleton", EntityType.Builder.create(MossySkeleton::new, SpawnGroup.MONSTER).setDimensions(0.6f, 1.99f).maxTrackingRange(8));
    public static final EntityType<JungleZombie> JUNGLE_ZOMBIE = register("jungle_zombie", EntityType.Builder.create(JungleZombie::new, SpawnGroup.MONSTER).setDimensions(0.6f, 1.95f).maxTrackingRange(8));

    public static void registerEntities() {
        REGISTER.forEach((name, entity) -> Registry.register(Registries.ENTITY_TYPE, new Identifier(UndergroundJungleMod.ID, name), entity));
    }

    public static void registerRenders() {
        EntityRendererRegistry.register(JUNGLE_ZOMBIE, JungleZombieRenderer::new);
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(JUNGLE_ZOMBIE, ZombieEntity.createZombieAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        EntityType<T> entityType = builder.build(name);
        REGISTER.put(name, entityType);
        return entityType;
    }
}
