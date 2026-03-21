package io.github.reneg.bossesdelight.common.mobeffects;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class BossesDelightMobEffect extends MobEffect {
    public BossesDelightMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -3355648);
    }

    public BossesDelightMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration > 0;
    }
}
