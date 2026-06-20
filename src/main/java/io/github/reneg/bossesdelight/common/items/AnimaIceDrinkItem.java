package io.github.reneg.bossesdelight.common.items;

import io.github.reneg.bossesdelight.common.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.List;

public class AnimaIceDrinkItem extends DrinkableItem {
    public AnimaIceDrinkItem(Properties properties) {
        super(properties, false, true);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects().stream().filter(e -> e.getAmplifier() < Config.ANIMA_DRINK_LEVEL.get() - 1).toList());
        if(!list.isEmpty()){
            MobEffectInstance effectInstance = list.get(consumer.getRandom().nextInt(list.size()));
            consumer.addEffect(new MobEffectInstance(effectInstance.getEffect(), effectInstance.getDuration(), effectInstance.getAmplifier() + 1));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, context, tooltip, isAdvanced);
        tooltip.add(Component.translatable("tooltip.bosses_delight.anima_ice_drink", Config.GLARE_SODA_LEVEL.get()).withStyle(ChatFormatting.BLUE));
    }
}
