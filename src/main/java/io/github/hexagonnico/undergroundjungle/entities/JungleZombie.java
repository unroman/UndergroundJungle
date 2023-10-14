package io.github.hexagonnico.undergroundjungle.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class JungleZombie extends ZombieEntity {

    public JungleZombie(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean flag = super.tryAttack(target);
        if(flag && this.getRandom().nextFloat() < 0.2f && this.getMainHandStack().isEmpty() && target instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60), this);
        }
        return flag;
    }
}
