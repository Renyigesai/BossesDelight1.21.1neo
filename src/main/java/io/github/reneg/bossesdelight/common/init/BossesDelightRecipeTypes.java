package io.github.reneg.bossesdelight.common.init;

import io.github.reneg.bossesdelight.common.recipes.SoulCookingPotRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.function.Supplier;

public class BossesDelightRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;
    public static final Supplier<RecipeType<SoulCookingPotRecipe>> SOUL_COOKING;

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<T>() {
            public String toString() {
                return "bosses_delight:" + identifier;
            }
        };
    }
    static {
        RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, "bosses_delight");
        SOUL_COOKING = RECIPE_TYPES.register("soul_cooking", () -> registerRecipeType("soul_cooking"));
    }

    public static class JEI{
        public static final mezz.jei.api.recipe.RecipeType<RecipeHolder<SoulCookingPotRecipe>> SOUL_COOKING = mezz.jei.api.recipe.RecipeType.createFromVanilla(BossesDelightRecipeTypes.SOUL_COOKING.get());
    }
}
