package io.github.reneg.bossesdelight.integration.category;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.init.BossesDelightBlock;
import io.github.reneg.bossesdelight.common.init.BossesDelightRecipeTypes;
import io.github.reneg.bossesdelight.common.recipes.SoulCookingPotRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.common.utility.RecipeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SoulCookingRecipeCategory implements IRecipeCategory<RecipeHolder<SoulCookingPotRecipe>>{

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(BossesDelight.MODID, "textures/gui/soul_cooking_pot.png");

    public final IDrawable back;
    public final IDrawable icon;
    protected final IDrawable heatIndicator;
    protected final IDrawable timeIcon;
    protected final IDrawable expIcon;
    protected final IDrawableAnimated arrow;

    public SoulCookingRecipeCategory(IGuiHelper helper) {
        this.back = helper.createDrawable(TEXTURE,29, 16, 116, 56);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BossesDelightBlock.SOUL_COOKING_POT.get()));
        this.heatIndicator = helper.createDrawable(TEXTURE, 176, 0, 17, 15);
        this.timeIcon = helper.createDrawable(TEXTURE, 176, 32, 8, 11);
        this.expIcon = helper.createDrawable(TEXTURE, 176, 43, 9, 9);
        this.arrow = helper.drawableBuilder(TEXTURE, 176, 15, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @SuppressWarnings("removal")
    @Override
    public @Nullable IDrawable getBackground() {
        return back;
    }

    @Override
    public RecipeType<RecipeHolder<SoulCookingPotRecipe>> getRecipeType() {
        return BossesDelightRecipeTypes.JEI.SOUL_COOKING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("bosses_delight.jei.soul_cooking");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SoulCookingPotRecipe> holder, IFocusGroup focusGroup) {
        SoulCookingPotRecipe recipe = holder.value();
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        ItemStack resultStack = RecipeUtils.getResultItem(recipe);
        ItemStack containerStack = recipe.getOutputContainer();
        int borderSlotSize = 18;

        for(int row = 0; row < 2; ++row) {
            for(int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeIngredients.size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, column * borderSlotSize + 1, row * borderSlotSize + 1).addItemStacks(Arrays.asList(((Ingredient)recipeIngredients.get(inputIndex)).getItems()));
                }
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 10).addItemStack(resultStack);
        if (!containerStack.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 63, 39).addItemStack(containerStack);
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 39).addItemStack(resultStack);
    }

    public void draw(RecipeHolder<CookingPotRecipe> holder, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.arrow.draw(guiGraphics, 60, 9);
        this.heatIndicator.draw(guiGraphics, 18, 39);
        this.timeIcon.draw(guiGraphics, 64, 2);
        if (((CookingPotRecipe)holder.value()).getExperience() > 0.0F) {
            this.expIcon.draw(guiGraphics, 63, 21);
        }

    }

    public List<Component> getTooltipStrings(RecipeHolder<CookingPotRecipe> holder, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        CookingPotRecipe recipe = (CookingPotRecipe)holder.value();
        if (ClientRenderUtils.isCursorInsideBounds(61, 2, 22, 28, mouseX, mouseY)) {
            List<Component> tooltipStrings = new ArrayList();
            int cookTime = recipe.getCookTime();
            if (cookTime > 0) {
                int cookTimeSeconds = cookTime / 20;
                tooltipStrings.add(Component.translatable("gui.jei.category.smelting.time.seconds", new Object[]{cookTimeSeconds}));
            }

            float experience = recipe.getExperience();
            if (experience > 0.0F) {
                tooltipStrings.add(Component.translatable("gui.jei.category.smelting.experience", new Object[]{experience}));
            }

            return tooltipStrings;
        } else {
            return Collections.emptyList();
        }
    }
}
