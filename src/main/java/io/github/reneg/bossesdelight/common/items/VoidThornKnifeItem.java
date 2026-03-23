package io.github.reneg.bossesdelight.common.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class VoidThornKnifeItem extends TippedKnifeItems {

    public VoidThornKnifeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity living, LivingEntity sliving) {
        living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 3000, 0));
        return true;
    }
}
