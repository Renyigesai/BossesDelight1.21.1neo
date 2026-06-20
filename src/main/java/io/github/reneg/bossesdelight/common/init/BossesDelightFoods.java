package io.github.reneg.bossesdelight.common.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class BossesDelightFoods {

    public static final FoodProperties BROKEN_OBSIDIAN_HEART =
            new FoodProperties.Builder().nutrition(4).saturationModifier(1.0f).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 0), 0.8F).build();

    public static final FoodProperties OBSIDIAN_SAUCE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();

    public static final FoodProperties OBSIDIAN_ONION = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0), 0.3F).build();

    public static final FoodProperties OBSIDIAN_RUNE_PUREE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.2f).alwaysEdible().effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2), 1.0F).effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 6000, 0), 1.0F).build();

    public static final FoodProperties OBSIDIAN_RUNE_COCKTAIL = new FoodProperties.Builder().nutrition(6).saturationModifier(1.0f).alwaysEdible().effect(() -> new MobEffectInstance(BossesDelightEffects.LAST_STAND, 1200, 0), 1.0F).build();

    public static final FoodProperties BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD = new FoodProperties.Builder().nutrition(16).saturationModifier(1.1f).alwaysEdible().effect(() -> new MobEffectInstance(BossesDelightEffects.LAST_STAND, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 6000, 1), 1.0F).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();

    public static final FoodProperties CRYSTAL_FRUIT_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(2.0f).effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1.0F).build();

    public static final FoodProperties CRYSTAL_CUP_CAKE = new FoodProperties.Builder().nutrition(8).saturationModifier(2.0f).alwaysEdible().effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 3), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1.0F).effect(new MobEffectInstance(MobEffects.JUMP, 1200, 1), 1.0F).build();

    public static final FoodProperties BLOSSOM_SALAD = new FoodProperties.Builder().nutrition(6).saturationModifier(2.0f).alwaysEdible().effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F).effect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F).build();

    public static final FoodProperties BOWL_OF_CRYSTAL_FRUIT_CUBE = new FoodProperties.Builder().nutrition(12).saturationModifier(1.2f).alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F)
            .build();

    public static final FoodProperties BLAZING_EYE_SHARDS = new FoodProperties.Builder().nutrition(4).saturationModifier(1.0f).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F).effect(() -> new MobEffectInstance(BossesDelightEffects.GAUNTLET_PROTECTION, 600, 0), 0.2F).build();
    public static final FoodProperties ANCIENT_ROLL = new FoodProperties.Builder().fast().nutrition(6).saturationModifier(1.2f).effect(()-> new MobEffectInstance(BossesDelightEffects.BREAKDOWN, 3600), 1.0F).build();
    public static final FoodProperties BLAZING_EYE_PIE_SLICE = new FoodProperties.Builder().nutrition(6).saturationModifier(1.0f)
                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1.0F)
            .effect(()-> new MobEffectInstance(BossesDelightEffects.GAUNTLET_PROTECTION, 600, 0), 0.6F)
            .build();
    public static final FoodProperties BOWL_OF_HAM_ABOVE_PALM = new FoodProperties.Builder().nutrition(14).saturationModifier(1.2f).alwaysEdible()
                    .effect(()->new MobEffectInstance(BossesDelightEffects.GAUNTLET_PROTECTION, 3600, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9600, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2), 1.0F)
            .effect(()->new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F)
            .build();

    public static final FoodProperties ANIMA_POPSICLE = new FoodProperties.Builder().nutrition(8).saturationModifier(1.1f).alwaysEdible()
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 3600, 0), 1.0F)
            .effect(()->new MobEffectInstance(BossesDelightEffects.INTANGIBLE, 1200, 0), 1.0F)
            .build();

    public static final FoodProperties BOWL_OF_LICH_SMOOTHIES = new FoodProperties.Builder().nutrition(12).saturationModifier(1.2f).alwaysEdible().effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 1), 1.0F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0), 1.0F).effect(()-> new MobEffectInstance(BossesDelightEffects.INTANGIBLE, 1200, 1), 1.0F).build();

    public static final FoodProperties BOWL_OF_MAGIC_FROZEN_NOODLES = new FoodProperties.Builder().nutrition(12).saturationModifier(1.2f).alwaysEdible()
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.JUMP, 6000, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 6000, 0), 1.0F)
            .effect(()->new MobEffectInstance(BossesDelightEffects.INTANGIBLE, 3600, 0), 1.0F)
            .build();

    public static final FoodProperties BOSSES_HODGEPODGE = new FoodProperties.Builder().nutrition(20).saturationModifier(1.0f).alwaysEdible()
                    .effect(()-> new MobEffectInstance(BossesDelightEffects.INTANGIBLE, 1200, 1), 1.0F)
            .effect(()-> new MobEffectInstance(BossesDelightEffects.GAUNTLET_PROTECTION, 1200, 1), 1.0F)
            .effect(()-> new MobEffectInstance(BossesDelightEffects.LAST_STAND, 1200, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F)
            .effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F)
            .build();

    public static final FoodProperties NECTAR_JELLY = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build();

    public static final FoodProperties OBSIDIAN_GLAZED_DRAGON_TONGUE = new FoodProperties.Builder().nutrition(16).saturationModifier(1.1f).alwaysEdible()
                    .effect(()-> new MobEffectInstance(BossesDelightEffects.LAST_STAND, 1200, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 4), 1.0F)
            .effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F)
            .build();

    public static final FoodProperties BOWL_OF_OBSIDIAN_GLAZED_DRAGON_BRAIN = new FoodProperties.Builder().nutrition(16).saturationModifier(1.1f).alwaysEdible()
                    .effect(()-> new MobEffectInstance(BossesDelightEffects.LAST_STAND, 3600, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 9600, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 1), 1.0F)
            .effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F)
            .build();

}

