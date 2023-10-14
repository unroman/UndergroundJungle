package io.github.hexagonnico.undergroundjungle.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MossySkeleton {

//    public static AttributeSupplier.Builder attributes() {
//        return Mob.createMobAttributes()
//            .add(Attributes.MAX_HEALTH, 20.0)
//            .add(Attributes.FOLLOW_RANGE, 32.0)
//            .add(Attributes.MOVEMENT_SPEED, 0.25)
//            .add(Attributes.ATTACK_DAMAGE, 3.0)
//            .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
//            .add(Attributes.ARMOR, 0.0)
//            .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0);
//    }

//    public MossySkeleton(EntityType<? extends AbstractSkeletonEntity> type, World world) {
//        super(type, world);
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.ENTITY_SKELETON_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSource) {
//        return SoundEvents.ENTITY_SKELETON_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.ENTITY_SKELETON_DEATH;
//    }
//
//    @Override
//    public SoundEvent getStepSound() {
//        return SoundEvents.ENTITY_SKELETON_STEP;
//    }
//
//    @Override
//    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
//        if(effect.getEffectType().equals(StatusEffects.POISON)) {
//            return false;
//        }
//        return super.canHaveStatusEffect(effect);
//    }
//
//    @Override
//    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
//        PersistentProjectileEntity projectile = super.createArrowProjectile(arrow, damageModifier);
//        if(projectile instanceof ArrowEntity arrowEntity) {
//            arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, 120));
//        }
//        return projectile;
//    }
}
