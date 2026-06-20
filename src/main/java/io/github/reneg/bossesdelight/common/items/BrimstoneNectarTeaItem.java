package io.github.reneg.bossesdelight.common.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.List;

public class BrimstoneNectarTeaItem extends DrinkableItem {
    public BrimstoneNectarTeaItem(Properties properties) {
        super(properties, false, true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        if(!level.isClientSide()){
            consumer.setHealth(consumer.getMaxHealth());
            List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects());
            for (MobEffectInstance ins : list) {
                consumer.removeEffect(ins.getEffect());
                if (consumer.hasEffect(ins.getEffect())) {
                    consumer.getActiveEffectsMap().remove(ins.getEffect());
                }
            }
        }
    }
}
