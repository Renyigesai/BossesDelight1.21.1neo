package io.github.reneg.bossesdelight.common.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ObsidianHeartKnifeItem extends TippedKnifeItems{


    public ObsidianHeartKnifeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if(entity instanceof LivingEntity living && selected){
            if(world.getGameTime() % 20L == 0L){
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
            }
        }
    }

    public boolean hurtEnemy(ItemStack p_40994_, LivingEntity p_40995_, LivingEntity p_40996_) {
        double d0 = p_40995_.getX() - p_40996_.getX();
        double d1 = p_40995_.getZ() - p_40996_.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        p_40995_.push(d0 / d2 * 2, 0.3D, d1 / d2 * 2);
        return true;
    }
}
