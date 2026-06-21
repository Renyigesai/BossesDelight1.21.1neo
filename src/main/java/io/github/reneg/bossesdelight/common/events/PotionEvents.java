package io.github.reneg.bossesdelight.common.events;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.init.BossesDelightEffects;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = BossesDelight.MODID)
public class PotionEvents {

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void onGauntletProtection(LivingIncomingDamageEvent event){
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            LivingEntity entity = event.getEntity();
            Holder<MobEffect> effect = BossesDelightEffects.GAUNTLET_PROTECTION;
            if(entity.hasEffect(effect) && !event.getSource().is(DamageTypes.GENERIC_KILL)){
                if(event.getSource().getEntity() == null) return;
                if(event.getSource().getEntity() instanceof LivingEntity living && living.hasEffect(BossesDelightEffects.BREAKDOWN)) return;
                float angle = Math.abs(event.getSource().getEntity().getYRot() - entity.getYRot()) % 360;
                if(angle < 90|| angle > 270){
                    event.setAmount((float) (event.getAmount() * Math.pow(0.5, entity.getEffect(effect).getAmplifier() + 1)));
                }
            }
        }
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void onIntangible(LivingIncomingDamageEvent event){
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            LivingEntity entity = event.getEntity();
            Holder<MobEffect> effect2 = BossesDelightEffects.INTANGIBLE;
            if(entity.hasEffect(effect2) && !event.getSource().is(DamageTypes.GENERIC_KILL)){
                if(entity.getRandom().nextInt(10) < 2 * (entity.getEffect(effect2).getAmplifier() + 1) || event.getSource().getDirectEntity() instanceof Projectile){
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ALLAY_ITEM_TAKEN, SoundSource.NEUTRAL, 1.0F, 0.3F);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLastStand(LivingIncomingDamageEvent event){
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            LivingEntity entity = event.getEntity();
            Holder<MobEffect> effect = BossesDelightEffects.LAST_STAND;

            if(entity.hasEffect(effect)){
                if(event.getAmount() >= entity.getHealth() && entity.getHealth() > 1 && !event.getSource().is(DamageTypes.GENERIC_KILL)){
                    event.setCanceled(true);
                    if(!entity.hasEffect(MobEffects.DAMAGE_RESISTANCE)){
                        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, (int) Math.pow(2, entity.getEffect(effect).getAmplifier() + 1) * 20, 4));
                    }
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ANVIL_HIT, SoundSource.NEUTRAL, 1.0F, 0.3F);
                    entity.setHealth(1);
                }
            }
        }
    }
}
