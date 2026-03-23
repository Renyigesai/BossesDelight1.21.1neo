package io.github.reneg.bossesdelight.common.init;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.recipes.SoulCookingPotRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BossesDelightRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
    public static final Supplier<RecipeSerializer<?>> SOUL_COOKING;

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, BossesDelight.MODID);
        SOUL_COOKING = RECIPE_SERIALIZERS.register("soul_cooking", SoulCookingPotRecipe.Serializer::new);
    }
}
