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
import java.util.Collections;
import java.util.List;

public class GlareStoutItem extends DrinkableItem {
    public GlareStoutItem(Properties properties) {
        super(properties, false, true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects());
        List<MobEffectInstance> newEffects = new ArrayList<>();
        List<Integer> durationList = new ArrayList<>();
        List<Integer> ampifierList = new ArrayList<>();
        if(!list.isEmpty()){
            for(MobEffectInstance effectInstance : list){
                if(effectInstance.getAmplifier() < Config.GLARE_SODA_LEVEL.get()){
                    durationList.add(effectInstance.getDuration());
                    ampifierList.add(effectInstance.getAmplifier());
                }
            }
            Collections.shuffle(durationList);
            Collections.shuffle(ampifierList);
            for(int i = 0; i < list.size(); ++i){
                newEffects.add(new MobEffectInstance(list.get(i).getEffect(), durationList.get(i), ampifierList.get(i)));
            }
            consumer.getActiveEffectsMap().clear();
            for(MobEffectInstance newEffect : newEffects){
                consumer.addEffect(newEffect);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, context, tooltip, isAdvanced);
        tooltip.add(Component.translatable("tooltip.bosses_delight.glare_stout", Config.GLARE_SODA_LEVEL.get()).withStyle(ChatFormatting.BLUE));
    }
}
