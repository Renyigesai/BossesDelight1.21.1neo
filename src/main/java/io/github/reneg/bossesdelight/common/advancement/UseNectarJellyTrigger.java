package io.github.reneg.bossesdelight.common.advancement;

import com.mojang.serialization.Codec;
import io.github.reneg.BossesDelight;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class UseNectarJellyTrigger extends SimpleCriterionTrigger<UseNectarJellyTrigger.TriggerInstance> {

    // 触发器唯一ID（需与进度JSON中的trigger字段一致）
    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(BossesDelight.MODID, "use_nectar_jelly");

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    // 无参数触发方法
    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    public record TriggerInstance() implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<TriggerInstance> CODEC = Codec.unit(new TriggerInstance());

        @Override
        public Optional<ContextAwarePredicate> player() {
            return Optional.empty();
        }
    }
}
