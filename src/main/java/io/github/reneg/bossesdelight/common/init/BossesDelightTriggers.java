package io.github.reneg.bossesdelight.common.init;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.advancement.UseNectarJellyTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BossesDelightTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Registries.TRIGGER_TYPE, BossesDelight.MODID);

    public static final Supplier<UseNectarJellyTrigger> USE_NECTAR_JELLY_TRIGGER =
            TRIGGERS.register("use_nectar_jelly", UseNectarJellyTrigger::new);
}
