package io.github.hexagonnico.undergroundjungle.renderers;

import io.github.hexagonnico.undergroundjungle.UndergroundJungleMod;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class JungleZombieRenderer extends ZombieEntityRenderer {

    private static final Identifier TEXTURE_LOCATION = new Identifier(UndergroundJungleMod.ID, "textures/entity/jungle_zombie.png");

    public JungleZombieRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE_LOCATION;
    }
}
