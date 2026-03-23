package io.github.reneg.bossesdelight.common.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class VoidThornKnifeItem extends TippedKnifeItems {

    public VoidThornKnifeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public boolean hurtEnemy(ItemStack p_40994_, LivingEntity p_40995_, LivingEntity p_40996_) {
        p_40994_.hurtAndBreak(1,p_40995_,LivingEntity.getSlotForHand(p_40995_.getUsedItemHand()));
        p_40995_.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 3000, 0));
        return true;
    }
}
