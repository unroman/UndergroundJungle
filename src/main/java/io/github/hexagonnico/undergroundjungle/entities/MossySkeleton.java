package io.github.hexagonnico.undergroundjungle.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MossySkeleton extends SkeletonEntity {

    public MossySkeleton(EntityType<? extends SkeletonEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean isConverting() {
        return false;
    }

    @Override
    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
        PersistentProjectileEntity projectile = super.createArrowProjectile(arrow, damageModifier);
        if(projectile instanceof ArrowEntity arrowEntity) {
            arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, 120));
        }
        return projectile;
    }
}
