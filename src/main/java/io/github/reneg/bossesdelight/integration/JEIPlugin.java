package io.github.reneg.bossesdelight.integration;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.client.gui.SoulCookingPotScreen;
import io.github.reneg.bossesdelight.common.init.BossesDelightItems;
import io.github.reneg.bossesdelight.common.init.BossesDelightRecipeTypes;
import io.github.reneg.bossesdelight.integration.category.SoulCookingRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(BossesDelight.MODID, "jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new SoulCookingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        registration.addRecipes(BossesDelightRecipeTypes.JEI.SOUL_COOKING, recipeManager.getAllRecipesFor(BossesDelightRecipeTypes.SOUL_COOKING.get()));
    }


    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BossesDelightItems.SOUL_COOKING_POT.get()), BossesDelightRecipeTypes.JEI.SOUL_COOKING);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration){
        registration.addRecipeClickArea(SoulCookingPotScreen.class, 89, 25, 24, 17, BossesDelightRecipeTypes.JEI.SOUL_COOKING);
    }


    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
