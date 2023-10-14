package io.github.hexagonnico.undergroundjungle.mixin;

import io.github.hexagonnico.undergroundjungle.UndergroundJungleItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
@SuppressWarnings("unused")
public class ItemEntityMixin {

    @SuppressWarnings("ConstantConditions")
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void damage(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> callbackInfo) {
        if((Object) this instanceof ItemEntity itemEntity) {
            if(!itemEntity.getStack().isEmpty() && (
                itemEntity.getStack().isOf(UndergroundJungleItems.TEMPLE_PICKAXE) ||
                    itemEntity.getStack().isOf(UndergroundJungleItems.TEMPLE_SWORD) ||
                    itemEntity.getStack().isOf(UndergroundJungleItems.TEMPLE_AXE) ||
                    itemEntity.getStack().isOf(UndergroundJungleItems.TEMPLE_SHOVEL) ||
                    itemEntity.getStack().isOf(UndergroundJungleItems.TEMPLE_HOE)
                ) && damageSource.isIn(DamageTypeTags.IS_EXPLOSION)) {
                callbackInfo.setReturnValue(false);
            }
        }
    }
}
