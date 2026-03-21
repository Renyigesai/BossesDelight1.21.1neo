package io.github.reneg.bossesdelight.common.items;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class BrimstoneNectarTeaItem extends DrinkableItem {
    public BrimstoneNectarTeaItem(Properties properties) {
        super(properties, false, true);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        Random random = new Random();
        List<Holder.Reference<MobEffect>> effects = BuiltInRegistries.MOB_EFFECT.holders().filter(holder -> {
            MobEffect effect = holder.value();
            return !effect.isInstantenous() && !effect.equals(MobEffects.SATURATION) && effect.isBeneficial();
        }).toList();
        Holder.Reference<MobEffect> randomEffect = effects.get(random.nextInt(effects.size()));
        if(!level.isClientSide()){
            int duration = 2000;
            consumer.addEffect(new MobEffectInstance(randomEffect, duration, 1));
        }
    }
}
