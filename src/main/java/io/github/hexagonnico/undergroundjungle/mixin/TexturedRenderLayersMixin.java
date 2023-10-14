package io.github.hexagonnico.undergroundjungle.mixin;

import io.github.hexagonnico.undergroundjungle.UndergroundJungleMod;
import io.github.hexagonnico.undergroundjungle.blocks.TempleChestBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TexturedRenderLayers.class)
@SuppressWarnings("unused")
public class TexturedRenderLayersMixin {

    private static final SpriteIdentifier TEMPLE_CHEST_TEXTURE = new SpriteIdentifier(TexturedRenderLayers.CHEST_ATLAS_TEXTURE, new Identifier(UndergroundJungleMod.ID, "entity/chest/temple"));

    @Inject(at = @At("HEAD"), method = "getChestTextureId", cancellable = true)
    private static void getChestTextureId(BlockEntity blockEntity, ChestType type, boolean christmas, CallbackInfoReturnable<SpriteIdentifier> callbackInfo) {
        if(blockEntity instanceof TempleChestBlockEntity) {
            callbackInfo.setReturnValue(TEMPLE_CHEST_TEXTURE);
        }
    }
}
