package io.github.reneg.bossesdelight.common.init;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.mobeffects.BossesDelightMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BossesDelightEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, BossesDelight.MODID);

    public static final Holder<MobEffect> LAST_STAND = EFFECTS.register("last_stand", BossesDelightMobEffect::new);
    public static final Holder<MobEffect> GAUNTLET_PROTECTION = EFFECTS.register("gauntlet_protection", BossesDelightMobEffect::new);
    public static final Holder<MobEffect> BREAKDOWN = EFFECTS.register("breakdown", BossesDelightMobEffect::new);
    public static final Holder<MobEffect> INTANGIBLE = EFFECTS.register("intangible", BossesDelightMobEffect::new);
}
